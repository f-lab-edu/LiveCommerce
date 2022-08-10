package com.flab.livecommerce.infrastructure.encryption;

import com.flab.livecommerce.domain.user.encryption.PasswordEncryption;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityPasswordEncoder implements PasswordEncryption {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean match(String rawPassword, String encryptedPassword) {
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }
}
