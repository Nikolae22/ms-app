package com.paymentservice.service.impl;

import com.paymentservice.dto.PaymentNotificationRequest;
import com.paymentservice.dto.PaymentRequest;
import com.paymentservice.mapper.PaymentMapper;
import com.paymentservice.notification.NotificationProducer;
import com.paymentservice.repository.PaymentRepository;
import com.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;


    @Override
    public Integer createPayment(PaymentRequest request) {
        var payment=repository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}
