package com.openclassrooms.mddapi.controller.user;

import com.openclassrooms.mddapi.config.JwtTokenUtil;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.LoginRegisterResponse;
import com.openclassrooms.mddapi.model.LoginRequest;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler assembler;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request){
        System.out.println("Hello");
        try{
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword(),new ArrayList<>());
            Authentication authentication = authenticationManager.authenticate(token);
            Optional<User> user = this.userRepository.findByEmail(request.getEmail());
            if(user.isPresent()) {
                String jwtToken = jwtTokenUtil.generateAccessToken(user.get());
                LoginRegisterResponse response = new LoginRegisterResponse(jwtToken);
                return ResponseEntity.ok().body(response);
            }else{
                return null;
            }
        }catch (BadCredentialsException exception){
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody @Valid User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userService.addUser(user);
            String token = jwtTokenUtil.generateAccessToken(savedUser);
            LoginRegisterResponse response = new LoginRegisterResponse(token);
            return ResponseEntity.ok().body(response);

        }catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
