package com.flab.livecommerce.user.application;

import com.flab.livecommerce.domain.user.PasswordEncryptor;

public final class FakePasswordEncryptor implements PasswordEncryptor {

    @Override
    public String encrypt(String text) {
        return text;
    }

    @Override
    public boolean match(String rawText, String encryptedText) {
        return rawText.equals(encryptedText);
    }
}
