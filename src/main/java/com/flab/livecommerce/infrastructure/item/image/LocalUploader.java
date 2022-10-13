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
     */
    private static final String LOCAL_PATH = "C:/study/";

    @Override
    public ItemImage upload(Long itemId, MultipartFile image) {
        String uploadFileName = getRandomFilename(image);
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

    private static String getRandomFilename(MultipartFile image) {
        String originalFilename = image.getOriginalFilename();
        String uploadFileName =
            UUID.randomUUID().toString().substring(0, 7) + "_" + originalFilename;
        return uploadFileName;
    }

    @Override
    public void deleteAll() {

    }


}
