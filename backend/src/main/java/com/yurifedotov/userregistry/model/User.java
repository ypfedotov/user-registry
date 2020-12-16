package com.yurifedotov.userregistry.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@ToString(exclude = "photo")
public class User extends UserSummary {
    public enum Gender {MALE, FEMALE}

    private String email;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String photo;
}
