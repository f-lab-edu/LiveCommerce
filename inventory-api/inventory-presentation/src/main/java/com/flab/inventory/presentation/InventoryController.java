package com.flab.inventory.presentation;

import com.flab.common.response.CommonApiResponse;
import com.flab.inventory.application.facade.InventoryManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/inventory")
@RestController
public class InventoryController {

    private final InventoryManager inventoryManager;

    public InventoryController(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;

    }

    //재고 등록
    public CommonApiResponse  createInventory() {
        return null;
    }

    //재고 검색
    @GetMapping("/{inventoryId}")
    public CommonApiResponse searchInventory(@PathVariable("inventoryId") Long id) {
        //inventoryManager.search(id);
        return null;
    }
}
