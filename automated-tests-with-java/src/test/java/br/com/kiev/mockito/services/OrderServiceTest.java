package br.com.kiev.mockito.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class OrderServiceTest {

    private final OrderService service = new OrderService();
    private final UUID defaultUUID = UUID.fromString("8d8b303-de52-4fc-a71c-9905a8043dac");
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2024, 7, 25, 15, 50);

    @DisplayName("Should Include Random OrderId When No OrderId Exists")
    @Test
    void testShouldIncludeRandomOrderId_When_NoOrderIdExissts() {
        try (MockedStatic<UUID> mockedUUID = mockStatic(UUID.class)) {
            mockedUUID.when(UUID::randomUUID).thenReturn(defaultUUID);
            var result = service.createOrder("Mac Book Pro", 2L, null);

            assertEquals(defaultUUID.toString(), result.getOrderId());
        }

    }

    @DisplayName("Should Include Current Time When Create a New Order")
    @Test
    void testShouldIncludeCurrentTime_When_CreateANewOrder() {
        try (MockedStatic<LocalDateTime> mockedUUID = mockStatic(LocalDateTime.class)) {
            mockedUUID.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);
            var result = service.createOrder("Mac Book Pro", 2L, null);

            assertEquals(defaultLocalDateTime, result.getCreationDateTime());
        }

    }
}