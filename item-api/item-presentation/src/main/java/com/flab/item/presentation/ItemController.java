package com.flab.item.presentation;

import com.flab.common.auth.AuthenticatedMember;
import com.flab.common.auth.Role;
import com.flab.common.auth.annotation.Authentication;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.response.CommonApiResponse;
import com.flab.item.application.facade.ItemManager;
import com.flab.item.presentation.request.RegisterItemRequest;
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

    @LoginCheck(authority = Role.SELLER)
    @PostMapping
    public CommonApiResponse registerItem(
        @Authentication AuthenticatedMember seller,
        @RequestBody @Valid RegisterItemRequest request
    ) {
        itemManager.register(seller.getId(), request.toCommand());
        return CommonApiResponse.success("OK");
    }

}
