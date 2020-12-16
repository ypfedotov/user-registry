package com.yurifedotov.userregistry.dataaccess;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "elasticsearch")
@Data
public class ElasticsearchProperties {
    private String hostname;
    private int port;
    private String indexName;
}
