package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.config.JwtService;
import com.openclassrooms.mddapi.model.Role;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.LoginRegisterResponse;
import com.openclassrooms.mddapi.payload.LoginRequest;
import com.openclassrooms.mddapi.payload.RegistrationRequest;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service for authentication and registration
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Authenticates a user based on the provided login credentials.
     *
     * @param request the login request containing user credentials
     * @return the response containing the generated JWT token and user email
     */
    public LoginRegisterResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return LoginRegisterResponse.builder().token(jwtToken).email(user.getEmail()).build();
    }

    /**
     * Registers a new user with the provided registration details.
     *
     * @param request the registration request containing user details
     * @return the response containing the generated JWT token and user email
     */
    public LoginRegisterResponse register(RegistrationRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return LoginRegisterResponse.builder().token(jwtToken).email(user.getEmail()).build();
    }

    /**
     * Clears the security context, effectively logging out the current user.
     */
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
