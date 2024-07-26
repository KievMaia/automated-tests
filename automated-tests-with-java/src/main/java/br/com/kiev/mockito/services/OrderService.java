package br.com.kiev.mockito.services;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderService {
    public Order createOrder(String productName, Long amount, String orderId) {
        Order order = new Order();

        order.setOrderId(orderId == null ? UUID.randomUUID().toString() : orderId);
        order.setProductName(productName);
        order.setAmount(amount);
        order.setCreationDateTime(LocalDateTime.now());

        return order;
    }

}
