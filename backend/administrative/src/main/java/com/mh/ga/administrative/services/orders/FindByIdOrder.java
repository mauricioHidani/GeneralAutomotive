package com.mh.ga.administrative.services.orders;

public interface FindByIdOrder<REQUEST, RESPONSE> {
    RESPONSE execute(REQUEST request);
}
