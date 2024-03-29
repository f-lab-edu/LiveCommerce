package com.flab.user.domain;

public interface PasswordEncryptor {

    String encrypt(String text);

    boolean match(String rawText, String encryptedText);
}
