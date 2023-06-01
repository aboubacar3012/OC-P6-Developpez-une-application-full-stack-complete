package com.openclassrooms.mddapi.controller.user;

import com.openclassrooms.mddapi.payload.LoginRequest;
import com.openclassrooms.mddapi.payload.RegistrationRequest;
import com.openclassrooms.mddapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        authService.logout();
        return ResponseEntity.ok().build();
    }

}
