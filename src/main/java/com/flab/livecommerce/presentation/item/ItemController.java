package com.flab.livecommerce.presentation.item;

import com.flab.livecommerce.application.item.facade.ItemManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.presentation.item.request.RegisterItemRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/item")
@RestController
public class ItemController {

    private final ItemManager itemManager;

    public ItemController(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    //todo 옵션그룹, 옵션이 JSON 변환 안되는 부분 빠르게 수정 필요
    @PostMapping
    public CommonApiResponse registerItem(@RequestBody @Valid RegisterItemRequest request) {
        itemManager.register(request.toCommand());
        return CommonApiResponse.success(null);
    }
}
