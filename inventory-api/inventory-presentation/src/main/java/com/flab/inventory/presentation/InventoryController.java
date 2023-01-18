package com.flab.inventory.presentation;

import com.flab.common.response.CommonApiResponse;
import com.flab.inventory.application.facade.InventoryManager;
import com.flab.inventory.presentation.request.CloseInventoryRequest;
import com.flab.inventory.presentation.request.DecreaseInventoryRequest;
import com.flab.inventory.presentation.request.IncreaseInventoryRequest;
import com.flab.inventory.presentation.request.OpenInventoryRequest;
import com.flab.inventory.presentation.request.ReduceInventoryRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/inventory")
@RestController
public class InventoryController {

    private final InventoryManager inventoryManager;

    public InventoryController(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    //재고 검색
    @GetMapping("/{inventoryId}")
    public CommonApiResponse<Object> searchInventory(
        @PathVariable("inventoryId") Long id
    ) {
        //inventoryManager.search(id);
        return null;
    }

    //판매 재개
    @PostMapping("/open")
    public CommonApiResponse openInventory(
        @RequestBody @Valid OpenInventoryRequest request
    ) {
        inventoryManager.open(request.toCommand());
        return CommonApiResponse.success("Ok");
    }

    //판매 중지
    @PostMapping("/close")
    public CommonApiResponse closeInventory(
        @RequestBody @Valid CloseInventoryRequest request
    ) {
        inventoryManager.close(request.toCommand());
        return CommonApiResponse.success("Ok");
    }

    //재고 변경
    @PostMapping("/increase")
    public CommonApiResponse increaseInventoryRequest(
        @RequestBody @Valid IncreaseInventoryRequest request
    ) {
        inventoryManager.increase(request.toCommand());
        return CommonApiResponse.success("Ok");
    }

    @PostMapping("/reduce")
    public CommonApiResponse reduceInventoryRequest(
        @RequestBody @Valid ReduceInventoryRequest request
    ) {
        inventoryManager.reduce(request.toCommand());
        return CommonApiResponse.success("Ok");
    }

    @PostMapping("/decrease")
    public CommonApiResponse decreaseInventoryRequest(
        @RequestBody @Valid DecreaseInventoryRequest request
    ) {
        inventoryManager.decrease(request.toCommand());
        return CommonApiResponse.success("Ok");
    }
}
