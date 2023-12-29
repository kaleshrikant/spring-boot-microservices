package com.spring.boot.inventoryservice.controller;

import com.spring.boot.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStack (@PathVariable("sku-code") String skuCode) {
        return inventoryService.isInStack(skuCode);
    }
}
