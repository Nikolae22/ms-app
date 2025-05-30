package com.orderservice.dto;

import com.orderservice.entity.PaymentMethod;
import com.orderservice.feignClient.CustomerResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
