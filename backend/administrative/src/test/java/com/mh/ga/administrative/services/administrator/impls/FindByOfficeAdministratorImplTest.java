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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FindByOfficeAdministratorImplTest {

    private AdministratorAdapter<Administrator, UUID> adapter;
    private FindByOfficeAdministratorImpl useCase;

    private Pageable pageable;

    private String existingOffice, nonExistingOffice;
    private Administrator entity;
    private AdministratorResponse response;

    @BeforeEach
    void setUp() throws Exception {
        adapter = mock(AdministratorAdapter.class);
        useCase = new FindByOfficeAdministratorImpl(adapter);

        UUID id = UUID.randomUUID();
        pageable = PageRequest.of(0, 12);

        entity = AdministratorFactory.createEntityByID(id);
        response = AdministratorFactory.createResponseByID(id.toString());

        existingOffice = entity.getOffice().toString();
        nonExistingOffice = "non_existing_office";

        when(adapter.findByOffice(existingOffice, pageable)).thenReturn(new PageImpl<>(List.of(entity)));
        when(adapter.findByOffice(nonExistingOffice, pageable)).thenReturn(new PageImpl<>(List.of()));
    }

    @Test
    @DisplayName("FindByOffice should return found page when was successfull found")
    void findByOffice_shouldReturnFoundPage_whenWasSuccessfullFound() {
        Page<AdministratorResponse> result = useCase.execute(existingOffice, pageable);

        verify(adapter).findByOffice(any(), any());

        assertFalse(result.isEmpty());
        assertTrue(result.toList().contains(response));
    }

    @Test
    @DisplayName("FindByOffice should return page result FindAll when Office name is blank")
    void findByOffice_shouldReturnPageResultFindAll_whenOfficeNameIsBlank() {
        when(adapter.findAll(pageable)).thenReturn(new PageImpl<>(List.of(entity)));

        Page<AdministratorResponse> result = useCase.execute("", pageable);

        verify(adapter).findAll(any());

        assertFalse(result.isEmpty());
        assertTrue(result.toList().contains(response));
    }

    @Test
    @DisplayName("FindByOffice should throw IllegalArgumentException when Office name is null")
    void findByOffice_shouldThrowIllegalArgumentException_whenOfficeNameIsNull() {
        String expectedErrorMessage = "Operation not valid on the requested system";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(null, pageable)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("FindByOffice should throw ResourceNotFoundException when resource not found by office")
    void findByOffice_shouldReturnResourceNotFoundException_whenResourceNotFoundByOffice() {
        String expectedErrorMessage = "Administrator not found with the requested Office name informated";

        Throwable result = assertThrows(
                ResourceNotFoundException.class,
                () -> useCase.execute(nonExistingOffice, pageable)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

}