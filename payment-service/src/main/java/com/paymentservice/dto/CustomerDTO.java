package com.paymentservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record CustomerDTO(
       String id,
       @NotNull(message = "Is required")
       String firstName,
       @NotNull(message = "Is required")
       String lastName,
       @NotNull(message = "Is required")
       @Email(message = "Email not valid")
       String email
) {
}
