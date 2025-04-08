package com.customerservice.dto;


import com.customerservice.entity.Address;

public record CustomerResponse(

        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
