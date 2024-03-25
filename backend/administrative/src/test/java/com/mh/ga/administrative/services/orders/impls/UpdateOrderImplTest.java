package com.mh.ga.administrative.services.orders.impls;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.factories.OrderFactory;
import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.services.exceptions.DataIntegrityException;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UpdateOrderImplTest {

    private OrderAdapter<Order, UUID> adapter;
    private UpdateOrderImpl useCase;

    private UUID existingId, nonExistingId;
    private String invalidId;
    private OrderRequest request;
    private OrderResponse response;

    @BeforeEach
    void setUp() throws Exception {
        adapter = mock(OrderAdapter.class);
        useCase = new UpdateOrderImpl(adapter);

        existingId = UUID.randomUUID();
        nonExistingId = UUID.randomUUID();
        invalidId = "invalidId";

        Order newEntity = OrderFactory.createNewEntityWithId(existingId);
        Order entity = OrderFactory.createEntityWithId(existingId);
        request = OrderFactory.createNewRequest();
        response = OrderFactory.createNewResponseWithId(existingId);

        when(adapter.findById(existingId)).thenReturn(Optional.of(entity));
        when(adapter.findById(nonExistingId)).thenReturn(Optional.empty());
        when(adapter.save(newEntity)).thenReturn(newEntity);
    }

    @Test
    @DisplayName("Update should return updated as an response when successfully update")
    void update_shouldReturnUpdatedAsAnResponse_whenSuccessfullyUpdate() {
        var result = useCase.execute(existingId.toString(), request);

        verify(adapter).findById(any());
        verify(adapter).save(any());

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
    @DisplayName("Update should throw an IllegalArgumentException when the ID is not valid, espacially for UUIDs")
    void update_shouldThrowAnIllegalArgumentException_whenTheIDIsNotValidEspaciallyForUUIDs() {
        String expectedErrorMessage = "Unable to proceed with the query with the requested information";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(invalidId, request)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Update should throw an IllegalArgumentException when the ID is null")
    void update_shouldThrowAnIllegalArgumentException_whenTheIDIsNull() {
        String expectedErrorMessage = "Operation not valid on the requested system";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(null, request)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Update should throw an IllegalArgumentException when request is null")
    void update_shouldThrowAnIllegalArgumentException_whenRequestIsNull() {
        var expectedErrorMessage = "It was not possible to proceed this process as the information provided is not " +
                "valid";

        var result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(existingId.toString(), null)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Update should throw a ResourceNotFoundException when Resource not found by ID")
    void update_shouldThrowAResourceNotFoundException_whenResourceNotFoundByID() {
        String expectedErrorMessage = "Order not found with the requested information";

        Throwable result = assertThrows(
                ResourceNotFoundException.class,
                () -> useCase.execute(nonExistingId.toString(), request)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Save should throw a DataIntegrityException when adapter not return result")
    void save_shouldThrowADataIntegrityException_whenAdapterNotReturnResult() {
        String expectedErrorMessage = "It was not possible to continue with the process due to an internal error " +
                "due to data not being entered";

        when(adapter.save(any())).thenReturn(null);

        Throwable result = assertThrows(
                DataIntegrityException.class,
                () -> useCase.execute(existingId.toString(), request)
        );

        verify(adapter).findById(any());
        verify(adapter).save(any());
        assertEquals(expectedErrorMessage, result.getMessage());
    }

}