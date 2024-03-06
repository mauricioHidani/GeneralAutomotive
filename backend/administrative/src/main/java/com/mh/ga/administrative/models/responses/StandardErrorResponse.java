package com.mh.ga.administrative.models.responses;

import java.time.Instant;

public record StandardErrorResponse(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
