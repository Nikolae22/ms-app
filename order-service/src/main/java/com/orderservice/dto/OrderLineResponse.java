package com.orderservice.dto;

public record OrderLineResponse(
    Integer id,
    double quantity
) {
}
