package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.image.exception.ItemImageNotFoundException;
import java.io.IOException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class GetImageProcessor {

    private final String uploadPath;

    public GetImageProcessor(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public Resource execute(String imagePath) throws IOException {
        Resource resource = new FileSystemResource(uploadPath + imagePath);

        if (!resource.exists()) {
            throw new ItemImageNotFoundException();
        }

        return resource;
    }
}
