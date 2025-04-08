package com.paymentservice.service;

import com.paymentservice.dto.PaymentRequest;

public interface PaymentService {
    Integer createPayment(PaymentRequest request);
}
