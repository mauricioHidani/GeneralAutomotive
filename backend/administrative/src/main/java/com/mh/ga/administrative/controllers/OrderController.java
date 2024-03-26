package com.mh.ga.administrative.controllers;

import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.services.orders.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/{orders}")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(
                service.findById(id)
        );
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> findByRegister(
            @RequestParam(required = false) Map<String, String> registers,
            Pageable pageable) {
        return ResponseEntity.ok(
                service.findByRegister(registers, pageable)
        );
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@Valid @RequestBody OrderRequest request) {
        OrderResponse result = service.save(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable(required = true) String id,
                                                @Valid @RequestBody OrderRequest request) {
        return ResponseEntity.ok(
                service.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
