package com.flab.user.infrastructure.encryption;

import com.flab.user.domain.PasswordEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserSecurityPasswordEncoder implements PasswordEncryptor {

    private final PasswordEncoder passwordEncoder;

    public UserSecurityPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean match(String rawPassword, String encryptedPassword) {
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }
}
