package com.openclassrooms.mddapi.payload;

import com.openclassrooms.mddapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRegisterResponse {
    private String token;
    private String email;
}