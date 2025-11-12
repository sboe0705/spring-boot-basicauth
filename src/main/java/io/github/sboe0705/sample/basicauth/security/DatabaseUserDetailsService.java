package io.github.sboe0705.sample.basicauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        String encryptedPassword = userRepository.getEncryptedPasswordByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return User.withUsername(username)
                .password(encryptedPassword)
                .roles("USER")
                .build();
    }

}
