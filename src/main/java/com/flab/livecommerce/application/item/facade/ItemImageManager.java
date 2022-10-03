package com.flab.livecommerce.application.item.facade;

import com.flab.livecommerce.application.item.UploadImageProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemImageManager {

    private final UploadImageProcessor uploadImageProcessor;

    public ItemImageManager(UploadImageProcessor uploadImageProcessor) {
        this.uploadImageProcessor = uploadImageProcessor;
    }

    public void uploadItemImage(MultipartFile thumbnailImage, MultipartFile[] specificImages) {
        uploadImageProcessor.execute(thumbnailImage, specificImages);
    }
}
