package com.flab.livecommerce.infrastructure.encryption;

import com.flab.livecommerce.domain.user.encryption.PasswordEncryption;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncryption implements PasswordEncryption {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordEncryption(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String encrypt(String password) {
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        return encryptedPassword;
    }

    @Override
    public boolean match(String rawPassword, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword);
    }
}
