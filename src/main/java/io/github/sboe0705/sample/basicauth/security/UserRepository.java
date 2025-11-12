package io.github.sboe0705.sample.basicauth.security;

import java.util.Optional;

public interface UserRepository {

    boolean existsByUsername(String username);

    void save(String username, String encryptedPassword);

    Optional<String> getEncryptedPasswordByUsername(String username);

}
