package com.mh.ga.administrative.services.orders.impls;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import com.mh.ga.administrative.services.orders.FindByRegisterOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.UUID;

public class FindByRegisterOrderImpl
        implements FindByRegisterOrder<
            Map<String, String>,
            Pageable,
            Page<OrderResponse>
        > {

    private final OrderAdapter<Order, UUID> adapter;

    public FindByRegisterOrderImpl(OrderAdapter<Order, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public Page<OrderResponse> execute(Map<String, String> request, Pageable pageable) {
        Page<OrderResponse> result;
        if (request.isEmpty()) {
            result = adapter.findAll(pageable).map(OrderResponse::toResponse);
        }
        else {
            Instant start, end;
            try {
                start = Instant.parse(request.get("start"));
                end = Instant.parse(request.get("end"));
            }
            catch (DateTimeParseException e) {
                throw new IllegalArgumentException(
                        "Date information is not valid to proceed with the operation"
                );
            }
            result = adapter.findByRegistered(start, end, pageable)
                    .map(OrderResponse::toResponse);
        }

        if (result.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Order not found with the requested Office name informated"
            );
        }

        return result;
    }

}
