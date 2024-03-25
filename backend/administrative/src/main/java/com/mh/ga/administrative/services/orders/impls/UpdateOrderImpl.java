package com.mh.ga.administrative.services.orders.impls;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.enums.OrderStatus;
import com.mh.ga.administrative.models.transfers.AdministratorIdentityRequest;
import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.models.transfers.ProductIdentityRequest;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.services.exceptions.DataIntegrityException;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import com.mh.ga.administrative.services.orders.UpdateOrder;

import java.util.UUID;

public class UpdateOrderImpl implements UpdateOrder<OrderRequest, String, OrderResponse> {

    private final OrderAdapter<Order, UUID> adapter;

    public UpdateOrderImpl(OrderAdapter<Order, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public OrderResponse execute(String id, OrderRequest request) {
        UUID validId;
        Order found;
        if (id == null) {
            throw new IllegalArgumentException(
                    "Operation not valid on the requested system"
            );
        }
        if (request == null) {
            throw new IllegalArgumentException(
                    "It was not possible to proceed this process as the information provided is not valid"
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

        found = adapter.findById(validId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with the requested information"
                ));

        Order saved = adapter.save(copyFields(found, request));
        if (saved == null) {
            throw new DataIntegrityException(
                    "It was not possible to continue with the process due to an internal error due to data not being " +
                    "entered"
            );
        }
        return OrderResponse.toResponse(saved);
    }

    private Order copyFields(Order found, OrderRequest update) {
        Order result = new Order(
                found.getId(),
                update.status() != null ? OrderStatus.valueOf(update.status()) : found.getStatus(),
                !update.description().isBlank() ? update.description() : found.getDescription(),
                update.registered() != null ? update.registered() : found.getRegistered(),
                update.liable() != null ? AdministratorIdentityRequest.toEntity(update.liable()) : found.getLiable()
        );
        result.getInventory().addAll(
                !update.inventory().isEmpty() ?
                        update.inventory().stream().map(ProductIdentityRequest::toEntity).toList() :
                        found.getInventory()
        );

        return result;
    }

}
