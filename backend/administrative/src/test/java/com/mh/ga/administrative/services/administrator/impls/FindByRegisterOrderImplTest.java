package com.mh.ga.administrative.services.administrator.impls;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.factories.OrderFactory;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import com.mh.ga.administrative.services.orders.impls.FindByRegisterOrderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FindByRegisterOrderImplTest {

    private OrderAdapter<Order, UUID> adapter;
    private FindByRegisterOrderImpl useCase;

    private Pageable pageable;
    private Map<String, String> existingRegistered, nonExistingRegistered;
    private Page<OrderResponse> expectedResultOnRegisteredFound;
    private Order entity;
    private OrderResponse response;

    @BeforeEach
    void setUp() throws Exception {
        adapter = mock(OrderAdapter.class);
        useCase = new FindByRegisterOrderImpl(adapter);

        UUID id = UUID.randomUUID();
        existingRegistered = Map.of(
                "start", "2023-11-01T09:30:00.00Z",
                "end", "2024-02-01T09:30:00.00Z"
        );
        nonExistingRegistered = Map.of(
                "start", "2020-01-01T09:00:00.00Z",
                "end", "2021-01-01T09:00:00.00Z"
        );
        entity = OrderFactory.createEntityWithId(id);
        response = OrderFactory.createResponseWithId(id);
        expectedResultOnRegisteredFound = new PageImpl<>(List.of(response));
        pageable = PageRequest.of(1, 12);

        when(adapter.findAll(any())).thenReturn(new PageImpl<>(List.of(entity)));
        when(adapter.findByRegistered(
                Instant.parse(existingRegistered.get("start")),
                Instant.parse(existingRegistered.get("end")),
                pageable
        )).thenReturn(new PageImpl<>(List.of(entity)));
        when(adapter.findByRegistered(
                Instant.parse(nonExistingRegistered.get("start")),
                Instant.parse(nonExistingRegistered.get("end")),
                pageable
        )).thenReturn(new PageImpl<>(List.of()));
    }

    @Test
    @DisplayName("FindByRegister should return page response when register start and end is null")
    void findByRegister_shouldReturnPageResponse_whenRegisterStartAndEndIsNull() {
        Page<OrderResponse> result = useCase.execute(existingRegistered, pageable);

        verify(adapter).findByRegistered(any(), any(), any());

        assertFalse(result.isEmpty());
        assertFalse(result.isEmpty());
        assertThat(result.getContent()).containsExactlyElementsOf(expectedResultOnRegisteredFound);
        assertThat(result).hasSize(expectedResultOnRegisteredFound.getSize());
    }

    @Test
    @DisplayName("FindByRegistered should throw IllegalArgumentException when register start or end is instant parse invalid")
    void findByRegistered_shouldThrowIllegalArgumentException_whenRegisterStartOrEndIsInstantParseInvalid() {
        Map<String, String> invalidStartRegistereds = Map.of(
                "start", "2024-01-01B09:00:00.00Z",
                "end", "2024-01-01D09:00:00.00Z"
        );
        String expectedErrorMessage = "Date information is not valid to proceed with the operation";

        Throwable result = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(invalidStartRegistereds, pageable)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("FindByRegister should return FindAll page result when register start and end is null")
    void findByRegister_shouldReturnFindAllPageResult_whenRegisterStartAndEndIsNull() {
        Page<OrderResponse> result = useCase.execute(Map.of(), pageable);

        verify(adapter).findAll(any());

        assertFalse(result.isEmpty());
        assertThat(result.getContent()).containsExactlyElementsOf(expectedResultOnRegisteredFound);
        assertThat(result).hasSize(expectedResultOnRegisteredFound.getSize());
    }

    @Test
    @DisplayName("FindByRegistered should throw ResourceNotFoundException when resource not found by registered")
    void findByRegistered_shouldReturnResourceNotFoundException_whenResourceNotFoundByRegistered() {
        String expectedErrorMessage = "Order not found with the requested Office name informated";

        when(adapter.findByRegistered(any(), any(), any())).thenReturn(new PageImpl<>(List.of()));

        Throwable result = assertThrows(
                ResourceNotFoundException.class,
                () -> useCase.execute(existingRegistered, pageable)
        );

        assertEquals(expectedErrorMessage, result.getMessage());
    }

    @Test
    @DisplayName("FindByAll should throw ResourceNotFoundException when resource not found")
    void findByAll_shouldReturnResourceNotFoundException_whenResourceNotFound() {
        String expectedErrorMessage = "Order not found with the requested Office name informated";

        when(adapter.findAll(any())).thenReturn(new PageImpl<>(List.of()));

        Throwable result = assertThrows(
                ResourceNotFoundException.class,
                () -> useCase.execute(Map.of(), pageable)
        );

        verify(adapter).findAll(any());

        assertEquals(expectedErrorMessage, result.getMessage());
    }

}