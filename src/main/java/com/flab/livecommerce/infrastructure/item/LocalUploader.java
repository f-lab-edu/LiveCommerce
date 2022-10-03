package com.flab.livecommerce.infrastructure.item;

import com.flab.livecommerce.domain.item.ImageUploader;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class LocalUploader implements ImageUploader {

    /*
     * 임의의 로컬 경로
     */
    private static final String LOCAL_PATH = "C:/study";

    @Override
    public String upload(MultipartFile image) {
        String originalFilename = image.getOriginalFilename();
        String uploadFileName =
            UUID.randomUUID().toString().substring(0, 10) + "_" + originalFilename;
        String uploadPath = LOCAL_PATH + uploadFileName;
        try {
            image.transferTo(new File(uploadPath));
            return uploadPath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
