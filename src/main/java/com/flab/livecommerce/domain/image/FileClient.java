package com.flab.livecommerce.domain.image;

import com.flab.livecommerce.domain.item.Item;
import java.util.List;

public interface FileClient {

    String getUploadPath(String imageDbPath);

    List<String> loadAll(Item item);
}
