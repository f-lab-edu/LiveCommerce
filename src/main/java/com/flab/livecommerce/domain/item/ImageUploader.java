package com.flab.livecommerce.domain.item;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {

    ItemImage uploadImage(MultipartFile image);

}
