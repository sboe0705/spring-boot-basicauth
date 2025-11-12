package io.github.sboe0705.sample.basicauth.security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Map<String, String> encryptedPasswordsByUsername = new HashMap<>();

    @PostConstruct
    void init() {
        encryptedPasswordsByUsername.put("alice", passwordEncoder.encode("alice"));
        encryptedPasswordsByUsername.put("bob", passwordEncoder.encode("bob"));
    }

    @Override
    public boolean existsByUsername(String username) {
        return encryptedPasswordsByUsername.containsKey(username);
    }

    @Override
    public void save(String username, String encryptedPassword) {
        encryptedPasswordsByUsername.putIfAbsent(username, encryptedPassword);
    }

    @Override
    public Optional<String> getEncryptedPasswordByUsername(String username) {
        return Optional.ofNullable(encryptedPasswordsByUsername.get(username));
    }

}
