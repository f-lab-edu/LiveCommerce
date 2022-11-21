package com.flab.livecommerce.infrastructure.image.local;

import com.flab.livecommerce.domain.image.FileUriGenerator;

public class LocalUriGenerator implements FileUriGenerator {

    @Override
    public String getUriPrefix() {
        return "http://localhost:8080/image/";
    }
}
