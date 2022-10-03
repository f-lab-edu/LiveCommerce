package com.flab.livecommerce.domain.item;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {
    String upload(MultipartFile image);
}
