package com.emre.security.controller;


import com.emre.security.model.AuthenticationRequest;
import com.emre.security.model.User;
import com.emre.security.repository.UserRepository;
import com.emre.security.service.CustomUserDetailsService;
import com.emre.security.util.JwtUtil;
import com.emre.security.util.TokenBlacklist;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//TEST FOR DEPLOYMENT6
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenBlacklist tokenBlacklist;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        // Encode the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // user.setRole("ROLE_USER"); // default role
        // Save the user to the database
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin Board";
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String userAccess() {
        return "User Board";
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = jwtUtil.extractTokenFromRequest(request);
        tokenBlacklist.addToBlacklist(token);

        // Clear any session-related data if necessary

        return ResponseEntity.ok("Logged out successfully");
    }
}