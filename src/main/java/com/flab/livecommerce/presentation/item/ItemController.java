package com.flab.livecommerce.presentation.item;

import com.flab.livecommerce.application.item.facade.ItemImageManager;
import com.flab.livecommerce.application.item.facade.ItemManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.presentation.item.request.ItemFormRequest;
import java.net.URI;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RequestMapping("/api/v1/item")
@RestController
public class ItemController {

    private final ItemManager itemManager;
    private final ItemImageManager itemImageManager;

    public ItemController(ItemManager itemManager, ItemImageManager itemImageManager) {
        this.itemManager = itemManager;
        this.itemImageManager = itemImageManager;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity registerItem(
        @RequestPart("ItemRegisterRequest") @Valid ItemFormRequest request,
        @RequestPart("thumbnailImg") MultipartFile thumbnailImage,
        @RequestPart(value = "specificImg", required = false) MultipartFile[] specificImages
    ) {
        Item registeredItem = itemManager.register(request.toCommand());

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .path("/image")
            .buildAndExpand(registeredItem.getId())
            .toUri();
        log.info("location:{}", location);

        //itemImageManager.uploadItemImage(thumbnailImage, specificImages);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{itemId}")
    public CommonApiResponse searchItem(@PathVariable("itemId") Long id) {
        var item = itemManager.search(id);
        return CommonApiResponse.success(item);
    }

    @PutMapping("/{itemId}")
    public CommonApiResponse editItem(
        @RequestBody @Valid ItemFormRequest request,
        @PathVariable("itemId") Long id
    ) {
        itemManager.update(request.toCommand(), id);
        return CommonApiResponse.success(null);
    }

    @DeleteMapping("/{itemId}")
    public CommonApiResponse deleteItem(@PathVariable("itemId") Long id) {
        itemManager.delete(id);
        return CommonApiResponse.success(null);
    }
}
