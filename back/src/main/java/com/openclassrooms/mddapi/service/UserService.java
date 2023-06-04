package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users.
     *
     * @return the list of all users
     */
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user
     * @return the user with the specified ID, or empty if not found
     */
    public Optional<User> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

    /**
     * Retrieves a user by email.
     *
     * @param email the email of the user
     * @return the user with the specified email, or empty if not found
     */
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    /**
     * Adds a new user.
     *
     * @param user the user to be added
     * @return the added user
     */
    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Replaces a user by ID with new user data.
     *
     * @param newUser the new user data
     * @param id      the ID of the user to be replaced
     * @return the replaced user, or the new user if the user was not found
     */
    public User replaceUserById(User newUser, Long id) {
        return this.userRepository.findById(id).map(user -> {
            if (newUser.getFirstName() != null) user.setFirstName(newUser.getFirstName());
            if (newUser.getLastName() != null) user.setLastName(newUser.getLastName());
            if (newUser.getEmail() != null) user.setEmail(newUser.getEmail());
            if (newUser.getProfile() != null) user.setProfile(newUser.getProfile());
            return userRepository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            return userRepository.save(newUser);
        });
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to be deleted
     */
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }
}
