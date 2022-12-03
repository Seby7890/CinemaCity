package com.spring.CinemaCity.service;

import com.spring.CinemaCity.DTO.RegisterDTO;
import com.spring.CinemaCity.model.Role;
import com.spring.CinemaCity.model.RoleType;
import com.spring.CinemaCity.model.User;
import com.spring.CinemaCity.repository.RoleRepository;
import com.spring.CinemaCity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public User register(RegisterDTO newUser) {
        Optional<User> foundUserOptional = userRepository.findUserByUsername(newUser.getUsername());

        if (foundUserOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CREATED, "Already exist");
        }

        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Role foundRole = roleRepository.findByRoleType(RoleType.ROLE_CLIENT);
        user.getRoleList().add(foundRole);
        foundRole.getUserList().add(user);
        return userRepository.save(user);
    }
}