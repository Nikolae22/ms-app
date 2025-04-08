package com.productservice.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Integer quantity,
        String categoryName,
        String categoryDescription
) {
}
