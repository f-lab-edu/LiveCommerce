package com.flab.livecommerce.domain.item;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {

    ItemImage upload(Long itemId, MultipartFile image);

    void deleteAll(List<String> deletedPath);
}
