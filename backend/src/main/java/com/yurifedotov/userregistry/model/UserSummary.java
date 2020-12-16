package com.yurifedotov.userregistry.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(exclude = "photo")
public class UserSummary {
    private String id;
    private String fullName;
    private String username;
}
