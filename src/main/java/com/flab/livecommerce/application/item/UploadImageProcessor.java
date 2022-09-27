package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.Item;
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

    // TODO 상세이미지
    public Item execute(Item item, MultipartFile thumbnailImg) {
        ItemImage thumbnail = uploadThumbnailToLocal(item, thumbnailImg);
        itemImageRepository.save(thumbnail);
        return null;
    }

    private ItemImage uploadThumbnailToLocal(Item item, MultipartFile thumbnailImg) {

        String originalFileName = thumbnailImg.getOriginalFilename();
        String uploadFileName = UUID.randomUUID().toString().substring(0,10) + "_" + originalFileName;
        String uploadPath = getLocalPath();
        try {
            thumbnailImg.transferTo(new File(uploadPath + uploadFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        item.setThumbnailImg(ItemImage.builder()
            .name(uploadFileName)
            .url(uploadPath)
            .itemId(item.getId())
            .build());

        return item.getThumbnailImg();
    }

    // TODO 경로 변경 필요
    private String getLocalPath() {
        return "C:/study/img";
    }
}
