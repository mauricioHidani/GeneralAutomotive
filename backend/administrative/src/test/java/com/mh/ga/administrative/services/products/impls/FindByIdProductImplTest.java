package com.mh.ga.administrative.services.products.impls;

import com.mh.ga.administrative.models.entities.Product;
import com.mh.ga.administrative.models.factories.ProductFactory;
import com.mh.ga.administrative.models.transfers.ProductResponse;
import com.mh.ga.administrative.repositories.adapter.ProductAdapter;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FindByIdProductImplTest {

    private ProductAdapter<Product, UUID> adapter;
    private FindByIdProductImpl useCase;

    private UUID existingId, nonExistingId;
    private String invalidId;
    private Product entity;
    private ProductResponse response;

    @BeforeEach
    void setUp() throws Exception {
        adapter = mock(ProductAdapter.class);
        useCase = new FindByIdProductImpl(adapter);

        existingId = UUID.randomUUID();
        nonExistingId = UUID.randomUUID();
        invalidId = "invalidId";

        entity = ProductFactory.createEntityWithID(existingId);
        response = ProductFactory.createResponseWithID(existingId);

        when(adapter.findById(existingId)).thenReturn(Optional.of(entity));
        when(adapter.findById(nonExistingId)).thenReturn(Optional.empty());
    }

    @Test
    @DisplayName("FindById should return response found when successfully found")
    void findById_shouldReturnResponseFound_whenSuccessfullyFound() {
        ProductResponse result = useCase.execute(existingId.toString());

        verify(adapter).findById(any());

        assertNotNull(result);
        assertTrue(result instanceof ProductResponse);
        assertEquals(result.id(), response.id());
        assertEquals(result.title(), response.title());
        assertEquals(result.description(), response.description());
        assertEquals(result.charge(), response.charge());
        assertEquals(result.weight(), response.weight());
        assertEquals(result.space(), response.space());
        assertEquals(result.unitCost(), response.unitCost());
    }

    @Test
    @DisplayName("FindByID should throw an IllegalArgumentException when the ID is not valid, espacially for UUIDs")
    void findByID_shouldThrowAnIllegalArgumentException_whenTheIDIsNotValidEspaciallyForUUIDs() {
        String expectedErrorMessage = "Unable to proceed with the query with the requested information";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                ()-> useCase.execute(invalidId)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("FindById should throw IllegalArgumentException when ID is null")
    void findById_shouldThrowIllegalArgumentException_whenIdIsNull() {
        String expectedErrorMessage = "Operation not valid on the requested system";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(null)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("FindByID should throw a ResourceNotFoundException when Resource not found by ID")
    void findByID_shouldThrowAResourceNotFoundException_whenResourceNotFoundByID() {
        String expectedErrorMessage = "Product not found with the requested information";
        when(adapter.findById(nonExistingId)).thenReturn(Optional.empty());

        Throwable result = assertThrows(
                ResourceNotFoundException.class,
                () -> useCase.execute(nonExistingId.toString())
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

}