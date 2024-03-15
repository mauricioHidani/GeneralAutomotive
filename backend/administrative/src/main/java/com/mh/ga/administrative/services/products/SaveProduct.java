package com.mh.ga.administrative.services.products;

public interface SaveProduct<REQUEST, RESPONSE> {
    RESPONSE execute(REQUEST request);
}
