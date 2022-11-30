package com.flab.user.infrastructure.encryption;

import com.flab.common.auth.PasswordEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserSecurityPasswordEncoder implements PasswordEncryptor {

    private final PasswordEncoder encoder;

    public UserSecurityPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.encoder = passwordEncoder;
    }

    @Override
    public String encrypt(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean match(String rawPassword, String encryptedPassword) {
        return encoder.matches(rawPassword, encryptedPassword);
    }
}
