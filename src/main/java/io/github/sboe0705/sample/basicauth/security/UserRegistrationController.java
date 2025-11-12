package io.github.sboe0705.sample.basicauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class UserRegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password) {
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        userRepository.save(username, passwordEncoder.encode(password));
        return ResponseEntity.ok("User registered");
    }

}
