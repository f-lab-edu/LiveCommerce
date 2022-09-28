package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.ItemImage;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class UploadImageProcessor {

    private final ItemImageRepository itemImageRepository;

    public UploadImageProcessor(ItemImageRepository itemImageRepository) {
        this.itemImageRepository = itemImageRepository;
    }

    public void execute(MultipartFile thumbnailImage, MultipartFile[] specificImages) {
        ItemImage thumbnail = uploadToLocal(thumbnailImage, specificImages);
        itemImageRepository.save(thumbnail);
    }

    private ItemImage uploadToLocal(MultipartFile thumbnailImage, MultipartFile[] specificImages) {

        String originalFileName = thumbnailImage.getOriginalFilename();
        String uploadFileName =
            UUID.randomUUID().toString().substring(0, 10) + "_" + originalFileName;
        String uploadPath = getLocalPath() + uploadFileName;
        try {
            thumbnailImage.transferTo(new File(uploadPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // TODO Item 객체 정보 저장, 상세 이미지 저장 - Util 클래스 분리 고려
        return null;
    }

    // TODO 경로 변경 필요
    private String getLocalPath() {
        return "C:/study/img";
    }
}
