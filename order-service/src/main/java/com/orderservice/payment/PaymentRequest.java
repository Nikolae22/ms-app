package com.orderservice.payment;

import com.orderservice.entity.PaymentMethod;
import com.orderservice.feignClient.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customerResponse
) {
}
