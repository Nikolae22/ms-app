package com.orderservice.dto;

import com.orderservice.entity.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Should be positive")
        BigDecimal amount,
        @NotNull(message =  "How u pay?")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be present")
        @NotEmpty
        @NotBlank
        String customerId,
        @NotEmpty(message = "U should buy something")
        List<PurchaseRequest> products
) {
}
