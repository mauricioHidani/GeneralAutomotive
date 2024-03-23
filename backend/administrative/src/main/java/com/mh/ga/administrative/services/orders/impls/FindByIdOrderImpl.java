package com.mh.ga.administrative.services.orders.impls;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import com.mh.ga.administrative.services.orders.FindByIdOrder;

import java.util.UUID;

public class FindByIdOrderImpl implements FindByIdOrder<UUID, OrderResponse> {

    private final OrderAdapter<Order, UUID> adapter;

    public FindByIdOrderImpl(OrderAdapter<Order, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public OrderResponse execute(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "Operation not valid on the requested system"
            );
        }

        Order found = adapter.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with the requested information"
                ));
        return OrderResponse.toResponse(found);
    }

}
