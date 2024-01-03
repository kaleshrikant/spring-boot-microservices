package com.spring.boot.order.service;

import com.spring.boot.order.dto.InventoryResponse;
import com.spring.boot.order.dto.OrderLineItemDto;
import com.spring.boot.order.dto.OrderRequest;
import com.spring.boot.order.model.Order;
import com.spring.boot.order.model.OrderLineItem;
import com.spring.boot.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    private final WebClient.Builder webClientBuilder;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItemList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemDto -> mapToOrderLineDto(orderLineItemDto))
                .collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItemList);

        /*
             *  CALL INVENTORY-SERVICE TO CHECK IF ORDER PRESENT IN STOCK  *
             *  PLACE ORDER IF IN STOCK ONLY !!  *
        */

        List<String> skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(orderLineItem -> orderLineItem.getSkuCode())
                .collect(Collectors.toList());

        InventoryResponse[] inventoryResponseArray = webClientBuilder.build()
                .get()
                .uri("http://INVENTORY-SERVICE/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

        if(allProductsInStock) {
            orderRepository.save(order);
            return "Order placed successfully.";
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later.");
        }
    }

    private OrderLineItem mapToOrderLineDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItem;
    }
}
