package com.yurifedotov.userregistry.dataaccess;

import com.yurifedotov.userregistry.model.User;
import com.yurifedotov.userregistry.model.UserSummary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRepository {
    List<UserSummary> findAllUserSummaries();

    User findById(@Param("userId") String userId);

    List<UserSummary> findUserSummariesByIds(List<String> userIds);

    void save(@Param("user") User user);

    boolean emailExists(@Param("email") String email);

    boolean usernameExists(@Param("username") String username);
}
