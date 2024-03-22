package com.mh.ga.administrative.services.administrator.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.factories.AdministratorFactory;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.models.utils.PersonDocumentUtil;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FindByDocAdministratorImplTest {

    private AdministratorAdapter<Administrator, UUID> adapter;
    private FindByDocAdministratorImpl useCase;

    private UUID id;
    private String existingDoc, nonExistingDoc, invalidDoc;
    private Administrator entity;
    private AdministratorResponse response;

    @BeforeEach
    void setUp() throws Exception {
        adapter = mock(AdministratorAdapter.class);
        useCase = new FindByDocAdministratorImpl(adapter);

        id = UUID.randomUUID();
        existingDoc = "123.456.789-09";
        nonExistingDoc = "600.437.645-09";
        invalidDoc = "123.456.789.90";

        entity = AdministratorFactory.createEntityByID(id);
        response = AdministratorFactory.createResponseByID(id);

        when(adapter.findByDocument(existingDoc)).thenReturn(entity);
        when(adapter.findByDocument(nonExistingDoc)).thenReturn(null);
    }

    @Test
    @DisplayName("FindByDoc should return response found when successfully found")
    void findByDoc_shouldReturnResponseFound_whenSuccessfullyFound() {
        AdministratorResponse result = useCase.execute(existingDoc);

        verify(adapter).findByDocument(any());

        assertTrue(result instanceof AdministratorResponse);
        assertNotNull(result);
        assertEquals(id.toString(), result.id());
        assertEquals(response.fullName(), result.fullName());
        assertEquals(response.document(), result.document());
        assertEquals(response.office(), result.office());
    }

    @Test
    @DisplayName("FindByDoc should throw an IllegalArgumentException when document is not valid")
    void findByDoc_shouldThrowAnIllegalArgumentException_whenDocumentIsNotValid() {
        try (MockedStatic<PersonDocumentUtil> util = mockStatic(PersonDocumentUtil.class)) {
            String expectedErrorMessage = "It is not possible to proceed with the operation with the informed document";

            util.when(() -> PersonDocumentUtil.isValid(invalidDoc)).thenReturn(false);

            Throwable result = assertThrows(
                    IllegalArgumentException.class,
                    () -> useCase.execute(invalidDoc)
            );

            assertEquals(expectedErrorMessage, result.getMessage());
        }
    }

    @Test
    @DisplayName("FindByDoc should throw an IllegalArgumentException when the document is null")
    void findByDoc_shouldThrowAnIllegalArgumentException_whenTheDocumentIsNull() {
        String invalidDoc = null;
        String expectedErrorMessage = "Operation not valid on the requested system";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(invalidDoc)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("FindByDoc should throw a ResourceNotFoundException when informed document does not exists")
    void findByDoc_shouldThrowAResourceNotFoundException_whenInformedDocumentDoesNotExists() {
        String expectedErrorMessage = "An Administrator was not found with the specified document";

        Throwable result = assertThrows(
                ResourceNotFoundException.class,
                () -> useCase.execute(nonExistingDoc)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

}