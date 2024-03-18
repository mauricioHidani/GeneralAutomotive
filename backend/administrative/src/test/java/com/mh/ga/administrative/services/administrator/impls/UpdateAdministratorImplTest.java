package com.mh.ga.administrative.services.administrator.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.enums.Offices;
import com.mh.ga.administrative.models.factories.AdministratorFactory;
import com.mh.ga.administrative.models.transfers.AdministratorRequest;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.models.utils.PersonDocumentUtil;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.services.exceptions.DataIntegrityException;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UpdateAdministratorImplTest {

    private AdministratorAdapter<Administrator, UUID> adapter;
    private UpdateAdministratorImpl useCase;

    private UUID existingId, nonExistingId;
    private String invalidId;
    private Administrator entity, newEntity;
    private AdministratorRequest request;
    private AdministratorResponse response;

    @BeforeEach
    void setUp() throws Exception {
        adapter = mock(AdministratorAdapter.class);
        useCase = new UpdateAdministratorImpl(adapter);

        existingId = UUID.randomUUID();
        nonExistingId = UUID.randomUUID();
        invalidId = "invalidId";

        entity = AdministratorFactory.createEntityByID(existingId);
        newEntity = AdministratorFactory.createNewEntityByID(existingId);
        request = AdministratorFactory.createNewRequest();
        response = AdministratorFactory.createNewResponseByID(existingId);

        when(adapter.findById(existingId)).thenReturn(Optional.of(entity));
        when(adapter.findById(nonExistingId)).thenReturn(Optional.empty());
        when(adapter.findByDocument(request.document())).thenReturn(null);
        when(adapter.save(newEntity)).thenReturn(newEntity);
    }

    @Test
    @DisplayName("Update should return updated as an response when successfully update")
    void update_shouldReturnUpdatedAsAnResponse_whenSuccessfullyUpdate() {
        AdministratorResponse result = useCase.execute(existingId.toString(), request);

        verify(adapter).findById(any());
        verify(adapter).findByDocument(any());
        verify(adapter).save(any());

        assertNotNull(result);
        assertTrue(result instanceof AdministratorResponse);
        assertEquals(existingId.toString(), result.id());
        assertEquals(response.fullName(), result.fullName());
        assertEquals(response.document(), result.document());
        assertEquals(response.office(), result.office());
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
    @DisplayName("Update should throw an IllegalArgumentException when request document is invalid")
    void update_shouldThrowAnIllegalArgumentException_whenRequestDocumentIsInvalid() {
        var expectedErrorMessage = "Unable to proceed with the invalid administrator document";
        var request = new AdministratorRequest(
                "123.456.789-12",
                "Nathalia Santos",
                Offices.BUYING_COORDINATOR.toString()
        );

        var result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(existingId.toString(), request)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Update should throw a DataIntegrityException when response document is not hidden")
    void update_shouldThrowADataIntegrityException_whenResponseDocumentIsNotHidden() {
        try (MockedStatic<PersonDocumentUtil> util = mockStatic(PersonDocumentUtil.class)) {
            String expectedErrorMessage = "Internal service error when trying to return the request response";

            util.when(() -> PersonDocumentUtil.isValid(any())).thenReturn(true);
            util.when(() -> PersonDocumentUtil.toHide(any())).thenReturn(request.document());
            util.when(() -> PersonDocumentUtil.isHidden(any())).thenReturn(false);

            var result = assertThrows(
                    DataIntegrityException.class,
                    () -> useCase.execute(existingId.toString(), request)
            );

            assertEquals(expectedErrorMessage, result.getMessage());
        }
    }

    @Test
    @DisplayName("Update should throw an IllegalArgumentException when request document already exists and diferent ID")
    void update_shouldThrowAnIllegalArgumentException_whenRequestDocumentAlreadyExists() {
        Administrator foundByDocument = AdministratorFactory.createEntityByID(UUID.randomUUID());
        String expectedErrorMessage = "The document provided is not valid to proceed with the request";
        when(adapter.findByDocument(any())).thenReturn(foundByDocument);

        var result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(existingId.toString(), request)
        );

        verify(adapter).findByDocument(any());

        assertNotEquals(foundByDocument.getId(), existingId);
        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Update should throw a ResourceNotFoundException when Resource not found by ID")
    void update_shouldThrowAResourceNotFoundException_whenResourceNotFoundByID() {
        String expectedErrorMessage = "Administrator not found with the requested information";

        Throwable result = assertThrows(
                ResourceNotFoundException.class,
                () -> useCase.execute(nonExistingId.toString(), request)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

}