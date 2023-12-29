package com.spring.boot.order.controller;

import com.spring.boot.order.dto.OrderRequest;
import com.spring.boot.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRquest) {
        orderService.placeOrder(orderRquest);
        return "Order placed successfully.";
    }
}
