package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    public User replaceUserById(User newUser, Long id) {
        return this.userRepository.findById(id)
                .map(user -> {
                    if (newUser.getUsername() != null) user.setUsername(newUser.getUsername());
                    if (newUser.getEmail() != null) user.setEmail(newUser.getEmail());
                    if (newUser.getProfile() != null) user.setProfile(newUser.getProfile());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }
}
