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

        String originalFilename = image.getOriginalFilename();
        String uploadFileName = createUploadFileName(originalFilename);

        String fullPath = getFullPath(uploadFileName);
        log.info("파일 저장 fullPath={}", fullPath);

        try {
            image.transferTo(new File(getFullPath(uploadFileName)));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return ItemImage.builder()
            .name(uploadFileName)
            .url(fullPath)
            .build();
    }

    private String getFullPath(String uploadFileName) {
        return localBasePath + uploadFileName;
    }

    private String createUploadFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
