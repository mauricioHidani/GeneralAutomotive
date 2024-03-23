package com.mh.ga.administrative.services.orders.impls;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import com.mh.ga.administrative.services.orders.DeleteOrder;

import java.util.UUID;

public class DeleteOrderImpl implements DeleteOrder<String> {

    private final OrderAdapter<Order, UUID> adapter;

    public DeleteOrderImpl(OrderAdapter<Order, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public void execute(String id) {
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

        if (!adapter.existingById(validId)) {
            throw new ResourceNotFoundException(
                    "Order not found with the requested information"
            );
        }

        adapter.deleteById(validId);
    }

}
