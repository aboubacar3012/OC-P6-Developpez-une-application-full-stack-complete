package com.openclassrooms.mddapi.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
@Data
public class LoginRequest {
    @NotNull
    @Email
    @Length(min = 5, max = 50, message = "Votre adresse mail doit etre compris entre 5 et 50 caracteres")

    private String email;

    @NotNull @Length(min = 3, max = 20, message = "Votre mot de passe  doit etre compris entre 5 et 20 caracteres")
    private String password;
}
