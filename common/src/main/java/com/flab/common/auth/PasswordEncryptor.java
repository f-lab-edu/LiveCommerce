package com.flab.common.auth;

public interface PasswordEncryptor {

    String encrypt(String text);

    boolean match(String rawText, String encryptedText);
}
