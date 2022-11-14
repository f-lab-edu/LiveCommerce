package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.ImageUploader;
import com.flab.livecommerce.domain.item.exception.ItemImageNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GetImageProcessor {

    private final ImageUploader imageUploader;


    public GetImageProcessor(ImageUploader imageUploader) {
        this.imageUploader = imageUploader;
    }


    public ResponseEntity<Resource> execute(String uploadPath) throws IOException {
        Resource resource = new FileSystemResource(imageUploader.getFullPath(uploadPath));

        if (!resource.exists()) {
            throw new ItemImageNotFoundException();
        }

        Path filePath = Paths.get(imageUploader.getFullPath(uploadPath));
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-Type", Files.probeContentType(filePath));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
