package com.yurifedotov.userregistry.dataaccess;

import com.yurifedotov.userregistry.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRepository {
    List<User> findAll();

    User findById(@Param("userId") String userId);

    void save(@Param("users") List<User> users);
}
