package com.mh.ga.administrative.services.orders.impls;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.services.exceptions.DataIntegrityException;
import com.mh.ga.administrative.services.orders.SaveOrder;

import java.util.UUID;

public class SaveOrderImpl implements SaveOrder<OrderRequest, OrderResponse> {

    private final OrderAdapter<Order, UUID> adapter;

    public SaveOrderImpl(OrderAdapter<Order, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public OrderResponse execute(OrderRequest order) {
        if (order == null) {
            throw new IllegalArgumentException(
                    "It was not possible to proceed this process as the information provided is not valid"
            );
        }

        Order saved = adapter.save(OrderRequest.toEntity(order));
        if (saved == null) {
            throw new DataIntegrityException(
                    "It was not possible to continue with the process due to an internal error due to data not " +
                            "being entered"
            );
        }
        return OrderResponse.toResponse(saved);
    }

}
