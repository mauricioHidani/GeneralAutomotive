package com.mh.ga.administrative.services.products;

public interface FindByIdProduct<REQUEST, RESPONSE> {
    RESPONSE execute(REQUEST request);
}
