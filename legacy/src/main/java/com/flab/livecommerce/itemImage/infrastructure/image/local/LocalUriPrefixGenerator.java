package com.flab.livecommerce.infrastructure.image.local;

import com.flab.livecommerce.domain.image.FileUriPrefixGenerator;

public class LocalUriPrefixGenerator implements FileUriPrefixGenerator {

    @Override
    public String generate() {
        return "http://localhost:8080/image/";
    }
}
