package com.mh.ga.administrative.controllers;

import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.services.orders.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/{orders}")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable UUID id) {
        throw new UnsupportedOperationException();
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest request) {
        OrderResponse result = service.save(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();
        return ResponseEntity.created(uri).body(result);
    }

}
