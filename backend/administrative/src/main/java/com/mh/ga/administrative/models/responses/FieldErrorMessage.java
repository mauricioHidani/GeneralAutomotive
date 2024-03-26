package com.mh.ga.administrative.models.responses;

public record FieldErrorMessage(
        String fieldName,
        String message
) {
}
