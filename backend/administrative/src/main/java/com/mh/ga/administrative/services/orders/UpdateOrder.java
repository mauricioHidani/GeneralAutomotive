package com.mh.ga.administrative.services.orders;

public interface UpdateOrder<REQUEST, IDENTITY, RESPONSE> {
    RESPONSE execute(IDENTITY identity, REQUEST request);
}
