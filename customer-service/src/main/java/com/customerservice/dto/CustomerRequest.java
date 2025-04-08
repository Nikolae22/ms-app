package com.customerservice.dto;

import com.customerservice.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
    String id,
    @NotNull(message = "Customer firstname is required")
    String firstName,
    @NotNull(message = "Customer lastname is required")
    String lastName,
    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid")
    String email,

    Address address
) {
}
