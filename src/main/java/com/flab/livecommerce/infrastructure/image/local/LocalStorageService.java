package com.flab.livecommerce.infrastructure.image.local;

import com.flab.livecommerce.domain.image.FileStorageService;
import com.flab.livecommerce.domain.image.ItemImage;
import com.flab.livecommerce.domain.image.exception.ItemImageNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class LocalStorageService implements FileStorageService {

    private final String basePath;

    public LocalStorageService(String basePath) {
        this.basePath = basePath;
    }


    @Override
    public ItemImage uploadImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new ItemImageNotFoundException();
        }

        String randomFileName = UUID.randomUUID().toString();
        String originalFilename = image.getOriginalFilename();
        String dbFilePath = createUploadFileName(originalFilename, randomFileName);
        log.info("uploadfilepath={}", dbFilePath);

        String localUploadPath = getUploadPath(dbFilePath);
        log.info("파일 저장 localUploadPath={}", localUploadPath);

        try {
            image.transferTo(new File(localUploadPath));
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return ItemImage.builder()
            .name(randomFileName)
            .url(dbFilePath)
            .build();
    }

    @Override
    public String getUploadPath(String imageDbPath) {
        return basePath + imageDbPath;
    }


    private String createUploadFileName(String originalFilename, String randomFileName) {
        String ext = extractExt(originalFilename);
        log.info("ext = {}", ext);
        return randomFileName + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
