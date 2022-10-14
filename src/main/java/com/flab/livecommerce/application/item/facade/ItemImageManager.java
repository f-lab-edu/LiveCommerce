package com.flab.livecommerce.application.item.facade;

import com.flab.livecommerce.application.item.DeleteImageProcessor;
import com.flab.livecommerce.application.item.UpdateImagePriorityProcessor;
import com.flab.livecommerce.application.item.UploadImageProcessor;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemImageManager {

    private final UploadImageProcessor uploadImageProcessor;
    private final DeleteImageProcessor deleteImageProcessor;

    private final UpdateImagePriorityProcessor updateImagePriorityProcessor;


    public ItemImageManager(
        UploadImageProcessor uploadImageProcessor,
        DeleteImageProcessor deleteImageProcessor,
        UpdateImagePriorityProcessor updateImagePriorityProcessor
    ) {
        this.uploadImageProcessor = uploadImageProcessor;
        this.deleteImageProcessor = deleteImageProcessor;
        this.updateImagePriorityProcessor = updateImagePriorityProcessor;
    }

    public void upload(Long itemId, MultipartFile thumbnailImage, MultipartFile[] specificImages)
        throws IOException {
        uploadImageProcessor.execute(itemId, thumbnailImage, specificImages);
    }

    public void delete(Long itemId, List<Integer> orderList) {
        deleteImageProcessor.execute(itemId, orderList);
    }

    public void updatePriority(Long itemId, List<Integer> orderList) {
        updateImagePriorityProcessor.execute(itemId, orderList);
    }
}
