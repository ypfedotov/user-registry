package com.yurifedotov.userregistry.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class User {
    private String id;
    private String fullName;
    private String email;
    private String username;
}
