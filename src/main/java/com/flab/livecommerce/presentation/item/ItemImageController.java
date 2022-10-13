package com.flab.livecommerce.presentation.item;

import com.flab.livecommerce.application.item.facade.ItemImageManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import java.io.IOException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/item")
@RestController
public class ItemImageController {

    private final ItemImageManager itemImageManager;

    public ItemImageController(ItemImageManager itemImageManager) {
        this.itemImageManager = itemImageManager;
    }

    @PostMapping("/{itemId}/image")
    public CommonApiResponse uploadItemImage(
        @PathVariable ("itemId") Long id,
        @RequestPart("thumbnailImg") MultipartFile thumbnailImage,
        @RequestPart(value = "specificImg", required = false) MultipartFile[] specificImages
    ) throws IOException {
        itemImageManager.uploadItemImage(id, thumbnailImage, specificImages);
        return CommonApiResponse.success(null);
    }
}
