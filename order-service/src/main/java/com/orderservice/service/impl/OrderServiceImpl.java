package com.orderservice.service.impl;

import com.orderservice.dto.*;
import com.orderservice.exception.BusinessException;
import com.orderservice.feignClient.CustomerClient;
import com.orderservice.kafka.OrderProducer;
import com.orderservice.mapper.OrderMapper;
import com.orderservice.payment.PaymentClient;
import com.orderservice.payment.PaymentRequest;
import com.orderservice.productClient.ProductClient;
import com.orderservice.repository.OrderRepository;
import com.orderservice.service.OrderLineService;
import com.orderservice.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;
    private final OrderMapper mapper;


    @Override
    public Integer createOrder(OrderRequest request) {
        // check the customer -> OpenFeign
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found"));

        // purchasde the products -> product ->ms(RestTemplate)
        var purchaseProducts = productClient.purchaseProducts(request.products());
        //persits order
        var order = orderRepository.save(mapper.toOrder(request));
        //persist order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        //  start payment process

        var paymentRequest = new PaymentRequest(
                request.id(),
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestPayment(paymentRequest);


        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        // send the order confirmation -> notification-ms (kafkabroker)
        return order.getId();
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("No order with this id"));
    }
}
