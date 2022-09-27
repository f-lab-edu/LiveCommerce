package com.flab.livecommerce.presentation.item;

import com.flab.livecommerce.application.item.facade.ItemManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.presentation.item.request.RegisterItemRequest;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/item")
@RestController
public class ItemController {

    private final ItemManager itemManager;

    public ItemController(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    // TODO 상세 이미지 리스트
    @PostMapping
    public CommonApiResponse registerItem(
        @Valid @RequestPart("itemData") RegisterItemRequest request,
        @RequestPart("thumbnailUrl") MultipartFile thumbnailImg
    ) throws IOException {
        itemManager.register(request.toCommand(), thumbnailImg);
        return CommonApiResponse.success(null);
    }

    @GetMapping("/{itemId}")
    public CommonApiResponse searchItem(@PathVariable("itemId") Long id) {
        var item = itemManager.search(id);
        return CommonApiResponse.success(item);
    }
}
