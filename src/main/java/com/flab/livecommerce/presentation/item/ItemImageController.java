package com.flab.livecommerce.presentation.item;

import com.flab.livecommerce.application.item.facade.ItemManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/item")
@RestController
public class ItemImageController {

    private final ItemManager itemManager;

    public ItemImageController(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @PostMapping("/{itemId}/image")
    public CommonApiResponse uploadItemImage(
        @RequestPart("thumbnailImg") MultipartFile thumbnailImage,
        @RequestPart(value = "specificImg", required = false) MultipartFile[] specificImages
    ) {
        itemManager.uploadItemImage(thumbnailImage, specificImages);
        return CommonApiResponse.success(null);
    }
}
