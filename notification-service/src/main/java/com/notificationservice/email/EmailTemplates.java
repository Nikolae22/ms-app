package com.notificationservice.email;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_CONFIRMATION("templates/payment-confirmation.html", "Payment successfully proccesed"),
    ORDER_CONFIRMATION("templates/order-confirmation.html", "Order Confirmation");

    @Getter
    private final String template;

    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
