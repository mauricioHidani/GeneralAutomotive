package com.mh.ga.administrative.services.administrator.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.factories.AdministratorFactory;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FindByIdAdministratorImplTest {

    private AdministratorAdapter<Administrator, UUID> adapter;
    private FindByIdAdministratorImpl useCase;

    private UUID existingID, nonExistingID;
    private Administrator entity;
    private AdministratorResponse response;

    @BeforeEach
    void setUp() throws Exception {
        adapter = mock(AdministratorAdapter.class);
        useCase = new FindByIdAdministratorImpl(adapter);

        existingID = UUID.randomUUID();
        nonExistingID = UUID.randomUUID();

        entity = AdministratorFactory.createEntityByID(existingID);
        response = AdministratorFactory.createResponseByID(existingID);

        when(adapter.findById(existingID)).thenReturn(Optional.of(entity));
    }

    @Test
    @DisplayName("FindByID should return response found when was successfully found")
    void findByID_shouldReturnResponseFound_whenWasSuccessfullyFound() {
        AdministratorResponse result = useCase.execute(existingID.toString());

        verify(adapter).findById(any());

        assertNotNull(result);
        assertTrue(result instanceof AdministratorResponse);
        assertEquals(existingID.toString(), result.id());
        assertEquals(response.fullName(), result.fullName());
        assertEquals(response.document(), result.document());
        assertEquals(result.office(), response.office());
    }

    @Test
    @DisplayName("FindByID should throw an IllegalArgumentException when the ID is not valid, espacially for UUIDs")
    void findByID_shouldThrowAnIllegalArgumentException_whenTheIDIsNotValidEspaciallyForUUIDs() {
        String invalidID = "aer3424324-invelid";
        String expectedErrorMessage = "Unable to proceed with the query with the requested information";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                ()-> useCase.execute(invalidID)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("FindByID should throw an IllegalArgumentException when the ID is null")
    void findByID_shouldThrowAnIllegalArgumentException_whenTheIDIsNull() {
        String invalidID = null;
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
        String expectedErrorMessage = "Administrator not found with the requested information";
        when(adapter.findById(nonExistingID)).thenReturn(Optional.empty());

        Throwable result = assertThrows(
                ResourceNotFoundException.class,
                () -> useCase.execute(nonExistingID.toString())
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

}