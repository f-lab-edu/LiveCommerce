package com.flab.livecommerce.application.item.facade;

import com.flab.livecommerce.application.item.DeleteImageProcessor;
import com.flab.livecommerce.application.item.GetImageProcessor;
import com.flab.livecommerce.application.item.UpdateImagePriorityProcessor;
import com.flab.livecommerce.application.item.UploadImageProcessor;
import com.flab.livecommerce.application.item.command.UpdateImageOrderingCommand;
import java.io.IOException;
import java.util.List;
import org.springframework.core.io.Resource;
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

    public List<String> upload(
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

    public void updatePriority(UpdateImageOrderingCommand command) {
        updateImagePriorityProcessor.execute(command);
    }

    public Resource getImage(String imagePath) throws IOException {
        return getImageProcessor.execute(imagePath);
    }
}
