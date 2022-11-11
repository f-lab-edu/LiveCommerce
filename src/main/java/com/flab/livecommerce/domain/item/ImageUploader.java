package com.flab.livecommerce.domain.item;

import java.net.URI;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {

    ItemImage uploadImage(MultipartFile image);

    String getFullPath(String uploadFileName);

    List<String> loadAlltest(Item item);

    List<URI> loadAll(Item item);
}
