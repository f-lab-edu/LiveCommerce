package com.flab.inventory.presentation;

import com.flab.common.response.CommonApiResponse;
import com.flab.inventory.application.facade.InventoryManager;
import com.flab.inventory.presentation.request.CloseInventoryRequest;
import com.flab.inventory.presentation.request.DecreaseInventoryRequest;
import com.flab.inventory.presentation.request.IncreaseInventoryRequest;
import com.flab.inventory.presentation.request.OpenInventoryRequest;
import com.flab.inventory.presentation.request.ReduceInventoryRequest;
import com.flab.inventory.presentation.response.InventoryResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/inventories")
@RestController
public class InventoryController {

    private final InventoryManager inventoryManager;

    public InventoryController(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    @PostMapping("/open")
    public CommonApiResponse<String> openInventory(
        @RequestBody @Valid OpenInventoryRequest request
    ) {
        inventoryManager.open(request.toCommand());
        return CommonApiResponse.success("Ok");
    }

    @PostMapping("/close")
    public CommonApiResponse<String> closeInventory(
        @RequestBody @Valid CloseInventoryRequest request
    ) {
        inventoryManager.close(request.toCommand());
        return CommonApiResponse.success("Ok");
    }

    @PostMapping("/increase")
    public CommonApiResponse<String> increaseInventoryRequest(
        @RequestBody @Valid IncreaseInventoryRequest request
    ) {
        inventoryManager.increase(request.toCommand());
        return CommonApiResponse.success("Ok");
    }

    @PostMapping("/reduce")
    public CommonApiResponse<String> reduceInventoryRequest(
        @RequestBody @Valid ReduceInventoryRequest request
    ) {
        inventoryManager.reduce(request.toCommand());
        return CommonApiResponse.success("Ok");
    }

    @PostMapping("/decrease")
    public CommonApiResponse<InventoryResponse> decreaseInventoryRequest(
        @RequestBody @Valid DecreaseInventoryRequest request
    ) {
        var result = inventoryManager.decrease(request.toCommand());
        return CommonApiResponse.success(new InventoryResponse(result.getInventoryData()));
    }
}
