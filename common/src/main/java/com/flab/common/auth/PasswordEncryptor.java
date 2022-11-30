package com.flab.common.auth;
// TODO 추후 위치 이동 고려 (리팩토링)
public interface PasswordEncryptor {

    String encrypt(String text);

    boolean match(String rawText, String encryptedText);
}
