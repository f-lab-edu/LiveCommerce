package com.flab.livecommerce.presentation.item;

import com.flab.livecommerce.application.item.facade.ItemImageManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.presentation.item.request.ItemOrderRequest;
import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/item/{itemId}/image")
@RestController
public class ItemImageController {

    private final ItemImageManager itemImageManager;

    public ItemImageController(ItemImageManager itemImageManager) {
        this.itemImageManager = itemImageManager;
    }

    @PostMapping
    public CommonApiResponse uploadItemImage(
        @PathVariable("itemId") Long id,
        @RequestPart("thumbnailImg") MultipartFile thumbnailImage,
        @RequestPart(value = "specificImg", required = false) MultipartFile[] specificImages
    ) throws IOException {
        itemImageManager.upload(id, thumbnailImage, specificImages);
        return CommonApiResponse.success(null);
    }


    @DeleteMapping
    public CommonApiResponse deleteItemImage(
        @PathVariable("itemId") Long itemId,
        @RequestBody List<Integer> orderList
    ) {
        itemImageManager.delete(itemId, orderList);
        return CommonApiResponse.success(null);
    }


    @PutMapping("/priority")
    public CommonApiResponse updateImagePriority(
        @RequestBody ItemOrderRequest orderingRequest
    ) {
        itemImageManager.updatePriority(orderingRequest.toCommand());
        return CommonApiResponse.success(null);
    }

}
