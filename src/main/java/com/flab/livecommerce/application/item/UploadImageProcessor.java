package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.ImageUploader;
import com.flab.livecommerce.domain.item.ItemImage;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import com.flab.livecommerce.domain.item.exception.ItemImageNotFoundException;
import java.io.IOException;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public void execute(Long itemId, MultipartFile thumbnailImage, MultipartFile[] specificImages)
        throws IOException {

        if (thumbnailImage.isEmpty()) {
            throw new ItemImageNotFoundException("썸네일 이미지는 필수입니다.");
        }

        ItemImage storedThumbnail = imageUploader.upload(itemId, thumbnailImage);
        itemImageRepository.save(storedThumbnail);

        for (int i = 1; i <= specificImages.length; i++) {
            if (!specificImages[i - 1].isEmpty()) {
                ItemImage storedSpecific = imageUploader.upload(itemId, specificImages[i - 1]);
                storedSpecific.setOrdering(i);
                itemImageRepository.save(storedSpecific);
            }
        }
    }
}
