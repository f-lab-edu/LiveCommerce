package com.flab.livecommerce.domain.user.encryption;

public interface PasswordEncryption {
    String encrypt(String password);

    boolean match(String rawPassword, String encryptedPassword);
}
