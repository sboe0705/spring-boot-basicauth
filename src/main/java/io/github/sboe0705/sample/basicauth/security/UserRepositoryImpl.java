package io.github.sboe0705.sample.basicauth.security;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, String> encryptedPasswordsByUsername = new HashMap<>();

    @PostConstruct
    void init() {
        encryptedPasswordsByUsername.put("alice", "$2a$10$a19mFFWkTbND6LyNCuiemu5ayRStJWzWjMCZHz5N9EfJCye4nXYta");
        encryptedPasswordsByUsername.put("bob", "$2a$10$I06pjb6wx9R/rcm98sbsYeNk6x/VJdYbSxmwM1fpVhGWnFqjb/MBi");
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
