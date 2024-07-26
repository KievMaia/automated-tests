package br.com.kiev.mockito.services;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    String productName;
    Long amount;
    String orderId;
    LocalDateTime creationDateTime;

    public Order() {
    }

    public Order(String productName, Long amount, String orderId, LocalDateTime creationDateTime) {
        this.productName = productName;
        this.amount = amount;
        this.orderId = orderId;
        this.creationDateTime = creationDateTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "productName='" + productName + '\'' +
                ", amount=" + amount +
                ", orderId='" + orderId + '\'' +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
