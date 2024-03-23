package com.mh.ga.administrative.services.orders.impls;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import com.mh.ga.administrative.services.orders.FindByIdOrder;

import java.util.UUID;

public class FindByIdOrderImpl implements FindByIdOrder<String, OrderResponse> {

    private final OrderAdapter<Order, UUID> adapter;

    public FindByIdOrderImpl(OrderAdapter<Order, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public OrderResponse execute(String id) {
        UUID validId;

        if (id == null) {
            throw new IllegalArgumentException(
                    "Operation not valid on the requested system"
            );
        }

        try {
            validId = UUID.fromString(id);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Unable to proceed with the query with the requested information"
            );
        }

        Order found = adapter.findById(validId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with the requested information"
                ));
        return OrderResponse.toResponse(found);
    }

}
