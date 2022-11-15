package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.image.exception.ItemImageNotFoundException;
import java.io.IOException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class GetImageProcessor {

    public Resource execute(String uploadPath) throws IOException {
        Resource resource = new FileSystemResource(uploadPath);

        if (!resource.exists()) {
            throw new ItemImageNotFoundException();
        }

        return resource;
    }
}
