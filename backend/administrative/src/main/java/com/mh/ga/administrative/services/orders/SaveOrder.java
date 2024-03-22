package com.mh.ga.administrative.services.orders;

public interface SaveOrder<REQUEST, RESPONSE> {
    RESPONSE execute(REQUEST request);
}
