package com.flab.livecommerce.infrastructure.item.image;

import com.flab.livecommerce.domain.item.ImageUploader;
import com.flab.livecommerce.domain.item.ItemImage;
import com.flab.livecommerce.domain.item.exception.ItemImageNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class LocalUploader implements ImageUploader {

    @Value("${file.dir}")
    private String localBasePath;

    @Override
    public ItemImage uploadImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new ItemImageNotFoundException();
        }

        String randomFileName = UUID.randomUUID().toString();
        String originalFilename = image.getOriginalFilename();
        String uploadFilePath = createUploadFileName(originalFilename, randomFileName);

        String fullPath = getFullPath(uploadFilePath);
        log.info("파일 저장 fullPath={}", fullPath);

        try {
            image.transferTo(new File(getFullPath(uploadFilePath)));
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return ItemImage.builder()
            .name(randomFileName)
            .url(uploadFilePath)
            .build();
    }

    private String getFullPath(String uploadFileName) {
        return localBasePath + uploadFileName;
    }

    private String createUploadFileName(String originalFilename, String randomFileName) {
        String ext = extractExt(originalFilename);
        return randomFileName + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
