package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.ImageUploader;
import com.flab.livecommerce.domain.item.ItemImage;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import com.flab.livecommerce.domain.item.exception.HasNoItemImagesException;
import org.springframework.web.multipart.MultipartFile;

public class UploadImageProcessor {

    private final ItemImageRepository itemImageRepository;
    private final ImageUploader imageUploader;

    public UploadImageProcessor(
        ItemImageRepository itemImageRepository,
        ImageUploader imageUploader
    ) {
        this.itemImageRepository = itemImageRepository;
        this.imageUploader = imageUploader;
    }

    public void execute(MultipartFile thumbnailImage, MultipartFile[] specificImages) {

        if (thumbnailImage.isEmpty()) {
            throw new HasNoItemImagesException("썸네일 이미지는 필수입니다.");
        }
        String thumbnailUrl = imageUploader.upload(thumbnailImage);
        itemImageRepository.save(ItemImage.builder().path(thumbnailUrl).basic(true).ordering(1).build());

        for (MultipartFile specificImage : specificImages) {
            if (!specificImage.isEmpty()) {
                String specificUrl = imageUploader.upload(specificImage);
                itemImageRepository.save(ItemImage.builder().path(specificUrl).basic(false).build());
            }
        }
    }
}
