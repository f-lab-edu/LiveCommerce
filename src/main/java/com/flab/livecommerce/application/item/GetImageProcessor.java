package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.ImageUploader;
import java.net.MalformedURLException;
import org.springframework.core.io.UrlResource;

public class GetImageProcessor {

    private final ImageUploader imageUploader;


    public GetImageProcessor(ImageUploader imageUploader) {
        this.imageUploader = imageUploader;
    }


    public UrlResource execute(String uploadPath) throws MalformedURLException {
        return new UrlResource("file:" + imageUploader.getFullPath(uploadPath));
    }
}
