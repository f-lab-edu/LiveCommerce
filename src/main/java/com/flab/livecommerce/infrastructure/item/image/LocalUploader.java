package com.flab.livecommerce.infrastructure.item.image;

import com.flab.livecommerce.domain.item.ImageUploader;
import com.flab.livecommerce.domain.item.ItemImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class LocalUploader implements ImageUploader {

    /*
     * 임의의 로컬 경로
     * // TODO 경로 변경
     */
    private static final String LOCAL_PATH = "C:/study/";

    @Override
    public ItemImage upload(Long itemId, MultipartFile image) {
        String uploadFileName = UUID.randomUUID().toString();
        String uploadPath = LOCAL_PATH + uploadFileName;

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
