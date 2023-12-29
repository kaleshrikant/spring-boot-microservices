package com.spring.boot.order.service;

import com.spring.boot.order.dto.OrderLineItemDto;
import com.spring.boot.order.dto.OrderRequest;
import com.spring.boot.order.model.Order;
import com.spring.boot.order.model.OrderLineItem;
import com.spring.boot.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItemList = orderRequest.getOrderLineItemList()
                .stream()
                .map(orderLineItemDto -> mapToOrderLineDto(orderLineItemDto))
                .collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItemList);
        orderRepository.save(order);
    }

    private OrderLineItem mapToOrderLineDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItem;
    }
}
