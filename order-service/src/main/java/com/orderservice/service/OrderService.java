package com.orderservice.service;

import com.orderservice.dto.OrderRequest;
import com.orderservice.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    Integer createOrder(OrderRequest request);

    List<OrderResponse> findAll();

    OrderResponse findById(Integer orderId);


}
