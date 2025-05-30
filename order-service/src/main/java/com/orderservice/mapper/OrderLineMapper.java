package com.orderservice.mapper;

import com.orderservice.dto.OrderLineRequest;
import com.orderservice.dto.OrderLineResponse;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(Order.builder()
                        .id(orderLineRequest.orderId())
                        .build())
                .productId(orderLineRequest.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(), orderLine.getQuantity()
        );
    }
}
