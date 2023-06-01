package com.openclassrooms.mddapi.controller.user;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.UserService;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler assembler;



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
    @GetMapping("/get")
    public Optional<User> getUserByEmail(@RequestParam String email) {
        Optional<User> user = this.userService.findByEmail(email);
        return Optional.of(user.get());
    }

    @PostMapping()
    public ResponseEntity<EntityModel<User>> addUser(@RequestBody User newUser){
        EntityModel<User> entityModel = assembler.toModel(this.userService.addUser(newUser));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
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
