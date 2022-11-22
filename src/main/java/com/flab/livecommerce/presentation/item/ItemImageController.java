package com.flab.livecommerce.presentation.item;

import com.flab.livecommerce.application.item.facade.ItemImageManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.presentation.item.request.UpdateImageOrderingRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RequestMapping
@RestController
public class ItemImageController {

    private final ItemImageManager itemImageManager;

    public ItemImageController(ItemImageManager itemImageManager) {
        this.itemImageManager = itemImageManager;
    }

    @PostMapping("/api/v1/item/{itemId}/image")
    public CommonApiResponse uploadItemImage(
        @PathVariable("itemId") Long id,
        @RequestPart("thumbnailImg") MultipartFile thumbnailImage,
        @RequestPart(value = "specificImg", required = false) MultipartFile[] specificImages
    ) throws IOException {
        var uploadPaths = itemImageManager.upload(id, thumbnailImage, specificImages);

        return CommonApiResponse.success(uploadPaths);
    }

    /*
     * 업로드 한 이미지 확인용 endpoint
     */
    @GetMapping("/image/{imagePath}")
    public ResponseEntity<Resource> getUploadItemImage(
        @PathVariable String imagePath
    ) throws IOException {
        Resource resource = itemImageManager.getImage(imagePath);
        Path filePath = Paths.get(resource.getURI());
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-Type", Files.probeContentType(filePath));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/item/{itemId}/image")
    public CommonApiResponse deleteItemImage(
        @PathVariable("itemId") Long itemId
    ) {
        itemImageManager.delete(itemId);
        return CommonApiResponse.success(null);
    }

    @PutMapping("/api/v1/item/image/priority")
    public CommonApiResponse updateImagePriority(
        @RequestBody UpdateImageOrderingRequest orderingRequest
    ) {
        itemImageManager.updatePriority(orderingRequest.toCommand());
        return CommonApiResponse.success(null);
    }

}
