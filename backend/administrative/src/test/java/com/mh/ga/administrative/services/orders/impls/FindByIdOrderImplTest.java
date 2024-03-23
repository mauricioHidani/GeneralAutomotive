package com.mh.ga.administrative.services.orders.impls;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.factories.OrderFactory;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FindByIdOrderImplTest {

    private OrderAdapter<Order, UUID> adapter;
    private FindByIdOrderImpl useCase;

    private UUID existingId, nonExistingId;
    private String invalidId;
    private Order entity;
    private OrderResponse response;

    @BeforeEach
    void setUp() throws Exception {
        adapter = mock(OrderAdapter.class);
        useCase = new FindByIdOrderImpl(adapter);

        existingId = UUID.randomUUID();
        nonExistingId = UUID.randomUUID();
        invalidId = "invalidId";
        entity = OrderFactory.createEntityWithId(existingId);
        response = OrderFactory.createResponseWithId(existingId);

        when(adapter.findById(existingId)).thenReturn(Optional.of(entity));
        when(adapter.findById(nonExistingId)).thenReturn(Optional.empty());
    }

    @Test
    @DisplayName("FindById should return order response when successfull")
    void findById_shouldReturnOrderResponse_whenSuccessfull() {
        OrderResponse result = useCase.execute(existingId.toString());

        assertNotNull(result);
        assertTrue(result instanceof OrderResponse);
        assertEquals(response.id(), result.id());
        assertEquals(response.status(), result.status());
        assertEquals(response.description(), result.description());
        assertEquals(response.registered(), result.registered());
        assertEquals(response.liable(), result.liable());
        assertTrue(result.inventory().containsAll(result.inventory()));
    }

    @Test
    @DisplayName("FindByID should throw an IllegalArgumentException when the ID is null")
    void findByID_shouldThrowAnIllegalArgumentException_whenTheIDIsNull() {
        String expectedErrorMessage = "Operation not valid on the requested system";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(null)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("FindByid should throw IllegalArgumentException when the ID is not valid, espacially for UUIDs")
    void findByid_shouldThrowIllegalArgumentException_whenTheIdIsNotValidEspaciallyForUUIDs() {
        String expectedErrorMessage = "Unable to proceed with the query with the requested information";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(invalidId)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("FindByID should throw a ResourceNotFoundException when Resource not found by ID")
    void findByID_shouldThrowAResourceNotFoundException_whenResourceNotFoundByID() {
        String expectedErrorMessage = "Order not found with the requested information";

        Throwable result = assertThrows(
                ResourceNotFoundException.class,
                () -> useCase.execute(nonExistingId.toString())
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

}