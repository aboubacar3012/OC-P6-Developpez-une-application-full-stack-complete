package com.openclassrooms.mddapi.model;

import lombok.Data;

@Data
public class LoginRegisterResponse {
    private String token;

    public LoginRegisterResponse(String token){
        this.token = token;
    }
}