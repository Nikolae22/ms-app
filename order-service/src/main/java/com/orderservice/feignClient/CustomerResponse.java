package com.orderservice.feignClient;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
