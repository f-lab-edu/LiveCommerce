package com.flab.livecommerce.infrastructure.item.image;

import com.flab.livecommerce.domain.item.ImageUploader;
import com.flab.livecommerce.domain.item.ItemImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class LocalUploader implements ImageUploader {

    @Value("${file.local.location}")
    private String localPath;

    @Override
    public ItemImage upload(Long itemId, MultipartFile image) {
        String uploadFileName = UUID.randomUUID().toString();
        String uploadPath = localPath + uploadFileName;

        try {
            image.transferTo(new File(uploadPath));
            return ItemImage.builder()
                .itemId(itemId)
                .url(uploadPath)
                .name(uploadFileName)
                .build();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
