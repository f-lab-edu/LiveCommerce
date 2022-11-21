package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.image.FileStorageService;
import com.flab.livecommerce.domain.image.exception.ItemImageNotFoundException;
import java.io.IOException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class GetImageProcessor {

    private final FileStorageService fileStorageService;

    public GetImageProcessor(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public Resource execute(String imagePath) throws IOException {
        String uploadPath = fileStorageService.getUploadPath(imagePath);
        Resource resource = new FileSystemResource(uploadPath);

        if (!resource.exists()) {
            throw new ItemImageNotFoundException();
        }

        return resource;
    }
}
