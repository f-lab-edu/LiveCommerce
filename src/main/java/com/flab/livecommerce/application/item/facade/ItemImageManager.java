package com.flab.livecommerce.application.item.facade;

import com.flab.livecommerce.application.item.DeleteImageProcessor;
import com.flab.livecommerce.application.item.UploadImageProcessor;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemImageManager {

    private final UploadImageProcessor uploadImageProcessor;
    private final DeleteImageProcessor deleteImageProcessor;


    public ItemImageManager(
        UploadImageProcessor uploadImageProcessor,
        DeleteImageProcessor deleteImageProcessor
    ) {
        this.uploadImageProcessor = uploadImageProcessor;
        this.deleteImageProcessor = deleteImageProcessor;
    }

    public void upload(Long itemId, MultipartFile thumbnailImage, MultipartFile[] specificImages)
        throws IOException {
        uploadImageProcessor.execute(itemId, thumbnailImage, specificImages);
    }

    public void delete(Long id, List<Integer> ordering) {
        deleteImageProcessor.execute(id, ordering);
    }
}
