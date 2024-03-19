package com.mh.ga.administrative.models.enums;

public enum OrderStatus {

    PENDING,
    APPROVED,
    REJECTED;

    public static OrderStatus toEnum(String str) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.toString().equals(str)) {
                return status;
            }
        }
        throw new IllegalArgumentException(
                "The information for type Order Status validation is not valid"
        );
    }

}
