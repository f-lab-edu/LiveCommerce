package com.flab.livecommerce.domain.image;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    ItemImage uploadImage(MultipartFile image);

}
