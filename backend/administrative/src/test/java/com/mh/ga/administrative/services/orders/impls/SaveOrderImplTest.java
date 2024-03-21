package com.mh.ga.administrative.services.orders.impls;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.factories.AdministratorFactory;
import com.mh.ga.administrative.models.factories.OrderFactory;
import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.services.exceptions.DataIntegrityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SaveOrderImplTest {

    private OrderAdapter<Order, UUID> adapter;
    private SaveOrderImpl useCase;

    private UUID id;
    private Order entity, entityWithoutId;
    private OrderRequest request;
    private OrderResponse response;

    @BeforeEach
    void setUp() throws Exception {
        adapter = mock(OrderAdapter.class);
        useCase = new SaveOrderImpl(adapter);

        id = UUID.randomUUID();

        entity = OrderFactory.createEntityWithId(id);
        entityWithoutId = OrderFactory.createEntityWithoutId();
        request = OrderFactory.createRequest();
        response = OrderFactory.createResponseWithId(id);

        when(adapter.save(entityWithoutId)).thenReturn(entity);
    }

    @Test
    @DisplayName("Save should return order response when successfull")
    void save_shouldReturnOrderResponse_whenSuccessfull() {
        OrderResponse result = useCase.execute(request);

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
    @DisplayName("Save should throw an IllegalArgumentException when request is null")
    void save_shouldThrowAnIllegalArgumentException_whenRequestIsNull() {
        String expectedErrorMessage = "It was not possible to proceed this process as the information provided is not " +
                "valid";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(null)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Save should throw a DataIntegrityException when adapter not return result")
    void save_shouldThrowADataIntegrityException_whenAdapterNotReturnResult() {
        String expectedErrorMessage = "It was not possible to continue with the process due to an internal error " +
                "due to data not being entered";

        when(adapter.save(entityWithoutId)).thenReturn(null);

        Throwable result = assertThrows(
                DataIntegrityException.class,
                () -> useCase.execute(request)
        );

        verify(adapter).save(any());
        assertEquals(expectedErrorMessage, result.getMessage());
    }

}