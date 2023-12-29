package com.spring.boot.inventoryservice.service;

import com.spring.boot.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStack (String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
