package com.mh.ga.administrative.services.products.impls;

import com.mh.ga.administrative.models.entities.Product;
import com.mh.ga.administrative.models.factories.ProductFactory;
import com.mh.ga.administrative.models.transfers.ProductRequest;
import com.mh.ga.administrative.models.transfers.ProductResponse;
import com.mh.ga.administrative.repositories.adapter.ProductAdapter;
import com.mh.ga.administrative.services.exceptions.DataIntegrityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SaveProductImplTest {

    private ProductAdapter<Product, UUID> adapter;
    private SaveProductImpl useCase;

    private Product entity, entitySaved;
    private ProductRequest request;
    private ProductResponse response;

    @BeforeEach
    void setUp() throws Exception {
        adapter = mock(ProductAdapter.class);
        useCase = new SaveProductImpl(adapter);

        UUID id = UUID.randomUUID();
        entity = ProductFactory.createEntityWithoutID();
        entitySaved = ProductFactory.createEntityWithID(id);
        request = ProductFactory.createRequest();
        response = ProductFactory.createResponseWithID(id);

        when(adapter.save(entity)).thenReturn(entitySaved);
    }

    @Test
    @DisplayName("Save should return Product saved when successfully saved")
    void save_shouldReturnProductSaved_whenSuccessfullySaved() {
        ProductResponse result = useCase.execute(request);

        verify(adapter).save(any());

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
    @DisplayName("Save should throw IllegalArgumentException when request is null")
    void save_shouldThrowIllegalArgumentException_whenRequestIsNull() {
        String expectedErrorMessage = "Unable to proceed with the request";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(null)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Save should throw DataIntegrityException when unable to save")
    void save_shouldReturnDataIntegrityException_whenUnableToSave() {
        String expectedErrorMessage = "It was not possible to continue executing the operation";
        when(adapter.save(entity)).thenReturn(null);

        Throwable result = assertThrows(
                DataIntegrityException.class,
                () -> useCase.execute(request)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

}