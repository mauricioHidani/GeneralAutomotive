package com.mh.ga.administrative.services.orders.impls;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class DeleteOrderImplTest {

    private OrderAdapter<Order, UUID> adapter;
    private DeleteOrderImpl useCase;

    private UUID existingId, nonExistingId;
    private String invalidId;

    @BeforeEach
    void setUp() throws Exception {
        adapter = mock(OrderAdapter.class);
        useCase = new DeleteOrderImpl(adapter);

        existingId = UUID.randomUUID();
        nonExistingId = UUID.randomUUID();
        invalidId = "invalidId";

        when(adapter.existingById(existingId)).thenReturn(true);
        when(adapter.existingById(nonExistingId)).thenReturn(false);
        doNothing().when(adapter).deleteById(existingId);
    }

    @Test
    @DisplayName("Delete should do nothing when successfully delete")
    void delete_shouldDoNothing_whenSuccessfullyDelete() {
        assertDoesNotThrow(() -> useCase.execute(existingId.toString()));

        verify(adapter).existingById(any());
        verify(adapter).deleteById(any());
    }

    @Test
    @DisplayName("Delete should throw IllegalArgumentException when the ID is not valid, espacially for UUIDs")
    void delete_shouldThrowIllegalArgumentException_whenTheIdIsNotValidEspaciallyForUUIDs() {
        String expectedErrorMessage = "Unable to proceed with the query with the requested information";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(invalidId)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Delete should throw an IllegalArgumentException when the ID is null")
    void delete_shouldThrowAnIllegalArgumentException_whenTheIDIsNull() {
        String expectedErrorMessage = "Operation not valid on the requested system";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(null)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Delete should throw ResourceNotFoundException when resource not found by id")
    void delete_shouldThrowResourceNotFoundException_whenResourceNotFoundById() {
        String expectedErrorMessage = "Order not found with the requested information";

        Throwable result = assertThrows(
                ResourceNotFoundException.class,
                () -> useCase.execute(nonExistingId.toString())
        );

        verify(adapter).existingById(any());

        assertEquals(expectedErrorMessage, result.getMessage());
    }

}