package com.flab.livecommerce.infrastructure.item.image.local;

import com.flab.livecommerce.domain.image.FileClient;
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

    private final FileClient localClient;


    public LocalStorageService(FileClient localClient) {
        this.localClient = localClient;
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

        String fullPath = localClient.getUploadPath(dbFilePath);
        log.info("파일 저장 fullPath={}", fullPath);

        try {
            image.transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return ItemImage.builder()
            .name(randomFileName)
            .url(dbFilePath)
            .build();
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
