package com.flab.livecommerce.application.item.facade;

import com.flab.livecommerce.application.item.DeleteImageProcessor;
import com.flab.livecommerce.application.item.GetImageProcessor;
import com.flab.livecommerce.application.item.UpdateImagePriorityProcessor;
import com.flab.livecommerce.application.item.UploadImageProcessor;
import com.flab.livecommerce.application.item.command.UpdateImageOrderCommand;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemImageManager {

    private final UploadImageProcessor uploadImageProcessor;
    private final DeleteImageProcessor deleteImageProcessor;

    private final UpdateImagePriorityProcessor updateImagePriorityProcessor;

    private final GetImageProcessor getImageProcessor;


    public ItemImageManager(
        UploadImageProcessor uploadImageProcessor,
        DeleteImageProcessor deleteImageProcessor,
        UpdateImagePriorityProcessor updateImagePriorityProcessor,
        GetImageProcessor getImageProcessor
    ) {
        this.uploadImageProcessor = uploadImageProcessor;
        this.deleteImageProcessor = deleteImageProcessor;
        this.updateImagePriorityProcessor = updateImagePriorityProcessor;
        this.getImageProcessor = getImageProcessor;
    }

    public List<URI> upload(
        Long itemId,
        MultipartFile thumbnailImage,
        MultipartFile[] specificImages
    )
        throws IOException {
        return uploadImageProcessor.execute(itemId, thumbnailImage, specificImages);
    }

    public void delete(Long itemId) {
        deleteImageProcessor.execute(itemId);
    }

    public void updatePriority(UpdateImageOrderCommand command) {
        updateImagePriorityProcessor.execute(command);
    }

    public ResponseEntity<Resource> get(String uploadPath) throws IOException {
        return getImageProcessor.execute(uploadPath);
    }
}
