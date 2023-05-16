package com.openclassrooms.mddapi.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class RegistrationRequest {
    @NotNull
    @Length(min = 3, max = 50)
    private String username;

    @NotNull
    @Email
    @Length(min = 5, max = 50)
    private String email;

    @NotNull
    @Length(min = 0, max = 10)
    private String password;
}