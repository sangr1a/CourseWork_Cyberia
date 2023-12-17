package com.example.cyberia.services;

import com.example.cyberia.models.User;
import com.example.cyberia.models.enums.Role;
import com.example.cyberia.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();
        String legalAddress = user.getLegalAddress();

        if (userRepository.findByEmail(email) != null || userRepository.findByLegalAddress(legalAddress) != null) return false;

        user.setActive(Objects.equals(legalAddress, ""));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (legalAddress != null && !legalAddress.isEmpty()) user.getRoles().add(Role.ROLE_ORG);
        else user.getRoles().add(Role.ROLE_VIEWER);

        log.info("Saving new User with email: {}", email);
        userRepository.save(user);
        return true;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userRepository.save(user);
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void updateUserProfile(Principal principal, User updatedUser) {
        User currentUser = getUserByPrincipal(principal);
        currentUser.setName(updatedUser.getName());
        currentUser.setPhoneNumber(updatedUser.getPhoneNumber());
        currentUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        userRepository.save(currentUser);
        log.info("User profile updated. User ID: {}", currentUser.getId());
    }

}
