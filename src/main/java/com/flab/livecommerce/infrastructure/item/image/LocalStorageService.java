package com.flab.livecommerce.infrastructure.item.image;

import com.flab.livecommerce.domain.item.FileStorageService;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemImage;
import com.flab.livecommerce.domain.item.exception.ItemImageNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
public class LocalStorageService implements FileStorageService {

    private static String localBasePath;

    @Value("${spring.servlet.multipart.location}")
    private void setLocalBasePath(String temp) {
        localBasePath = temp;
    }

    @Override
    public ItemImage uploadImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new ItemImageNotFoundException();
        }

        String randomFileName = UUID.randomUUID().toString();
        String originalFilename = image.getOriginalFilename();
        String uploadFilePath = createUploadFileName(originalFilename, randomFileName);
        log.info("uploadfilepath={}", uploadFilePath);

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

    @Override
    public String getFullPath(String uploadFileName) {
        log.info("localbasepath={}", localBasePath);
        return localBasePath + uploadFileName;
    }

    @Override
    public List<URI> loadAll(Item item) {
        return item.getItemImages().stream().map(
            itemImage -> ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uploadPath}")
                .buildAndExpand(itemImage.getUrl())
                .toUri()
        ).collect(Collectors.toList());
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
