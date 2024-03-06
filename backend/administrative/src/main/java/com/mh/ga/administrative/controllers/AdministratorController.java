package com.mh.ga.administrative.controllers;

import com.mh.ga.administrative.models.transfers.AdministratorRequest;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.services.administrator.AdministratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(
        value = "/administrators",
        produces = "application/vnd.ga-administrative+json;version=1.0"
)
public class AdministratorController {

    private final AdministratorService service;

    public AdministratorController(AdministratorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministratorResponse> findById(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }

    @PostMapping
    public ResponseEntity<AdministratorResponse> save(@RequestBody AdministratorRequest request) {
        var response = service.save(request);
        var uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

}
