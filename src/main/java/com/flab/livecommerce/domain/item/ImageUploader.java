package com.flab.livecommerce.domain.item;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {

    ItemImage upload(Long itemId, MultipartFile image);

}
