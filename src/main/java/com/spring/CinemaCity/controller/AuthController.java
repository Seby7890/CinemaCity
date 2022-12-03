package com.spring.CinemaCity.controller;

import com.spring.CinemaCity.DTO.AuthDTO;
import com.spring.CinemaCity.DTO.RegisterDTO;
import com.spring.CinemaCity.model.User;
import com.spring.CinemaCity.service.JwtTokenService;
import com.spring.CinemaCity.service.UserDetailsServiceImpl;
import com.spring.CinemaCity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthDTO user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return jwtTokenService.generateToken(userDetails);
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterDTO newUser) {
        return userService.register(newUser);
    }
}