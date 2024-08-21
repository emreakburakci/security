package com.emre.security.service;

import com.emre.security.model.AuthenticationRequest;
import com.emre.security.model.User;
import com.emre.security.repository.UserRepository;
import com.emre.security.util.JwtUtil;
import com.emre.security.util.TokenBlacklist;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthService {

    private AuthenticationManager authenticationManager;

    private CustomUserDetailsService userDetailsService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private JwtUtil jwtUtil;

    private TokenBlacklist tokenBlacklist;

    public ResponseEntity<User> registerUser(User user) {
        // Encode the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // user.setRole("ROLE_USER"); // default role
        // Save the user to the database
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    public String loginUser(AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;


    }

    public String hello() {
        return "Hello, World!";
    }

    public String adminAccess() {
        return "Admin Board";
    }

    public String userAccess() {
        return "User Board";
    }

    public String logout(HttpServletRequest request) {
        String token = jwtUtil.extractTokenFromRequest(request);
        tokenBlacklist.addToBlacklist(token);

        // Clear any session-related data if necessary

        return "Logged out successfully";
    }
}
