package com.flab.livecommerce.domain.user;

public interface Encryption {
    String encrypt(String text);

    boolean match(String rawText, String encryptedText);
}