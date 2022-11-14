package com.flab.livecommerce.presentation.item;

import com.flab.livecommerce.application.item.facade.ItemManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.presentation.item.request.RegisterItemRequest;
import com.flab.livecommerce.presentation.item.request.UpdateItemRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/item")
@RestController
public class ItemController {

    private final ItemManager itemManager;

    public ItemController(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @PostMapping
    public CommonApiResponse registerItem(
        @RequestBody @Valid RegisterItemRequest request
    ) {
        itemManager.register(request.toCommand());
        return CommonApiResponse.success(null);
    }

    @GetMapping("/{itemId}")
    public CommonApiResponse searchItem(@PathVariable("itemId") Long id) {
        var itemInfo = itemManager.search(id);
        return CommonApiResponse.success(itemInfo);
    }

    @PutMapping("/{itemId}")
    public CommonApiResponse updateItem(
        @RequestBody @Valid UpdateItemRequest request,
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
