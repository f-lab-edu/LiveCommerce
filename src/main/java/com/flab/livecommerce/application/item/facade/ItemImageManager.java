package com.flab.livecommerce.application.item.facade;

import com.flab.livecommerce.application.item.UploadImageProcessor;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemImageManager {

    private final UploadImageProcessor uploadImageProcessor;

    public ItemImageManager(UploadImageProcessor uploadImageProcessor) {
        this.uploadImageProcessor = uploadImageProcessor;
    }

    public void uploadItemImage(Long itemId, MultipartFile thumbnailImage, MultipartFile[] specificImages)
        throws IOException {
        uploadImageProcessor.execute(itemId, thumbnailImage, specificImages);
    }
}
