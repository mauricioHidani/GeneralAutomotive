package com.mh.ga.administrative.services.administrator.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.enums.Offices;
import com.mh.ga.administrative.models.factories.AdministratorFactory;
import com.mh.ga.administrative.models.transfers.AdministratorRequest;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.models.utils.PersonDocumentUtil;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.services.exceptions.DataIntegrityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SaveAdministratorImplTest {

    private AdministratorAdapter<Administrator, UUID> adapter;
    private SaveAdministratorImpl useCase;


    private UUID id;


    @BeforeEach
    void setUp() throws Exception {
        adapter = mock(AdministratorAdapter.class);
        useCase = new SaveAdministratorImpl(adapter);

        id = UUID.randomUUID();

        when(adapter.save(any())).thenReturn(AdministratorFactory.createEntityByID(id));
        when(adapter.findByDocument(any())).thenReturn(null);
    }

    @Test
    @DisplayName("Save Administrator Should Return Administrator DTO Response When Successful")
    void saveAdministrator_shouldReturnAdministratorDTOResponse_whenSuccessful() {
        var responseDocument = AdministratorFactory.createResponseWithoudID().document();

        var request = AdministratorFactory.createRequest();

        var result = useCase.execute(request);

        verify(adapter).save(any());
        assertTrue(result instanceof AdministratorResponse);
        assertNotNull(result);
        assertEquals(id, UUID.fromString(result.id()));
        assertEquals(request.fullName(), result.fullName());
        assertEquals(responseDocument, result.document());
        assertEquals(request.office(), result.office());
    }

    @Test
    @DisplayName("Save Administrator Should Return Illegal Argument Exception When Request Is Null")
    void saveAdministrator_shouldReturnIllegalArgumentException_whenRequestIsNull() {
        var expectedErrorMessage = "It was not possible to proceed this process as the information provided is not " +
                "valid";

        var result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(null)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Save Administrator Should Return Illegal Argument Exception When Request Document Is Invalid")
    void saveAdministrator_shouldReturnIllegalArgumentException_whenRequestDocumentIsInvalid() {
        var expectedErrorMessage = "Unable to proceed with the invalid administrator document";
        var request = new AdministratorRequest(
                "123.456.789-12",
                "Nathalia Santos",
                Offices.BUYING_COORDINATOR.toString()
        );

        var result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(request)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Save Administrator Should Return Data Integrity Exception When Response Document Is Not Hidden")
    void saveAdministrator_shouldReturnDataIntegrityException_whenResponseDocumentIsNotHidden() {
        try (MockedStatic<PersonDocumentUtil> util = mockStatic(PersonDocumentUtil.class)) {
            var document = AdministratorFactory.createRequest().document();
            var expectedErrorMessage = "Internal service error when trying to return the request response";

            util.when(() -> PersonDocumentUtil.isValid(any())).thenReturn(true);
            util.when(() -> PersonDocumentUtil.toHide(any())).thenReturn(document);

            var result = assertThrows(
                    DataIntegrityException.class,
                    () -> useCase.execute(AdministratorFactory.createRequest())
            );

            assertEquals(expectedErrorMessage, result.getMessage());
        }
    }

    @Test
    @DisplayName("Save Administrator Should Return Illegal Argument Exception When Request Document Already Exists")
    void saveAdministrator_shouldReturnIllegalArgumentException_whenRequestDocumentAlreadyExists() {
        var expectedErrorMessage = "The document provided is not valid to proceed with the request";
        when(adapter.findByDocument(any())).thenReturn(AdministratorFactory.createEntityByID(UUID.randomUUID()));

        var result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(AdministratorFactory.createRequest())
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Save Administrator Should Return Illegal Argument Exception When Request Office Is Not Among The Elements Of The Enumerator")
    void saveAdministrator_shouldReturnIllegalArgumentException_whenRequestOfficeIsNotAmongTheElementsOfTheEnumerator() {
        var expectedErrorMessage = "It is not possible to proceed with the operation, the administrator's office entered is not valid";
        var request = new AdministratorRequest(
                "123.456.789-09",
                "Nathalia Santos",
                "Invalid_Office"
        );

        var result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(request)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("Save Administrator Should Return Data Integrity Exception When Adapter Not Return Result")
    void saveAdministrator_shouldReturnDataIntegrityException_whenAdapterNotReturnResult() {
        var expectedErrorMessage = "It was not possible to continue with the process due to an internal error due to " +
                "data not being entered";

        when(adapter.save(AdministratorFactory.createEntityWithoutID())).thenReturn(null);

        var result = assertThrows(
                DataIntegrityException.class,
                () -> useCase.execute(AdministratorFactory.createRequest())
        );

        verify(adapter).save(any());
        assertEquals(expectedErrorMessage, result.getMessage());
    }

}