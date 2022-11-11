package com.flab.livecommerce.presentation.item;

import com.flab.livecommerce.application.item.facade.ItemImageManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.presentation.item.request.ItemOrderRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
        var uploadUriList = itemImageManager.upload(id, thumbnailImage, specificImages);
        return CommonApiResponse.success(uploadUriList);
    }

    /*
     * 업로드 한 이미지 확인용 endpoint
     */
    @GetMapping("/{uploadPath}")
    public Resource getUploadItemImage(
        @PathVariable String uploadPath
    ) throws MalformedURLException {
        return itemImageManager.get(uploadPath);
    }

    @DeleteMapping
    public CommonApiResponse deleteItemImage(
        @PathVariable("itemId") Long itemId
    ) {
        itemImageManager.delete(itemId);
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
