package com.flab.livecommerce.domain.item;

import java.net.URI;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    ItemImage uploadImage(MultipartFile image);

    String getFullPath(String uploadFileName);

    List<URI> loadAll(Item item);
}
