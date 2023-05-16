package com.openclassrooms.mddapi.controller.user;

import com.openclassrooms.mddapi.config.JwtTokenUtil;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.LoginRegisterResponse;
import com.openclassrooms.mddapi.model.LoginRequest;
import com.openclassrooms.mddapi.model.RegistrationRequest;
import com.openclassrooms.mddapi.model.User;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/auth")
public class UserController {
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



    @GetMapping()
    public CollectionModel<EntityModel<User>> getUsers(){
        List<EntityModel<User>> users =  this.userService.getUsers().stream()
                .map(user -> assembler.toModel(user))
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).getUsers()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable Long id) {
        User user = this.userService.getUserById(id).orElseThrow(() -> new NotFoundException(id));
        return assembler.toModel(user);
    }

//    @PostMapping()
//    public ResponseEntity<EntityModel<User>> addUser(@RequestBody User newUser){
//        EntityModel<User> entityModel = assembler.toModel(this.userService.addUser(newUser));
//        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request){
        try{
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            User user = (User) authentication.getPrincipal();
            String jwtToken = jwtTokenUtil.generateAccessToken(user);
            LoginRegisterResponse response = new LoginRegisterResponse(jwtToken);
            return ResponseEntity.ok().body(response);
        }catch (BadCredentialsException exception){
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

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@RequestBody User newUser, @PathVariable Long id){
        EntityModel<User> entityModel = assembler.toModel(this.userService.replaceUserById(newUser,id));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        this.userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
