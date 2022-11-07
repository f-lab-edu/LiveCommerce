package com.flab.livecommerce.item.application;

import com.flab.livecommerce.item.domain.Item;
import com.flab.livecommerce.item.domain.ItemImage;
import com.flab.livecommerce.item.domain.ItemImageRepository;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class UploadImageProcessor {

    private final ItemImageRepository itemImageRepository;

    public UploadImageProcessor(ItemImageRepository itemImageRepository) {
        this.itemImageRepository = itemImageRepository;
    }

    public void execute(Item item, MultipartFile thumbnailImg) {
        ItemImage thumbnail = uploadThumbnailToLocal(item, thumbnailImg);
        itemImageRepository.save(thumbnail);
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

        //TODO 다정님이 구현하실 파트
        return null;
    }

    // TODO 경로 변경 필요
    private String getLocalPath() {
        return "C:/study/img";
    }
}
