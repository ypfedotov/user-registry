package com.yurifedotov.userregistry.dataaccess;

import com.yurifedotov.userregistry.model.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class UserSearchRepository {
    private static final String FULL_NAME_FIELD = "fullName";
    private static final Logger logger = LoggerFactory.getLogger(UserSearchRepository.class);

    private final ElasticsearchProperties props;
    private RestHighLevelClient client;

    public UserSearchRepository(ElasticsearchProperties props) {
        this.props = props;
    }

    @PostConstruct
    public void init() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(props.getHostname(), props.getPort(), "http"))
        );
    }

    public void index(List<User> users) {
        try {
            BulkRequest request = new BulkRequest();
            for (User user: users) {
                request.add(new IndexRequest(props.getIndexName())
                        .id(user.getId())
                        .source(XContentType.JSON,
                                FULL_NAME_FIELD, user.getFullName()
                        ));
            }

            BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
            for (BulkItemResponse item : response.getItems()) {
                logger.debug("{} {} {}", item.status(), item.isFailed(), item.getFailureMessage());
            }

            logger.debug("Indexed {} users into {} index in {}", users.size(), props.getIndexName(), response.getTook());

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public List<String> findUserIdsByName(String query) {
        try {
            SearchRequest request = new SearchRequest(props.getIndexName());
            request.source().query(QueryBuilders.matchQuery("fullName", query));

            SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            return Arrays.stream(response.getHits().getHits())
                    .map(SearchHit::getId)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @PreDestroy
    public void close() throws IOException {
        client.close();
    }
}
