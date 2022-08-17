package com.flab.livecommerce.domain.user;

public interface PasswordEncryptor {

    String encrypt(String text);

    boolean match(String rawText, String encryptedText);
}
