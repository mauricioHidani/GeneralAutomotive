package com.mh.ga.administrative.models.responses;

import java.time.Instant;
import java.util.List;

public record ValidationErrorMessage(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path,
        List<FieldErrorMessage> errors
) {
    public void addErorr(FieldErrorMessage error) {
        this.errors.add(error);
    }
}
