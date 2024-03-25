package com.mh.ga.administrative.services.orders;

import org.springframework.data.domain.Pageable;

public interface FindByRegisterOrder<REQUEST, PAGEABLE, RESPONSE> {
    RESPONSE execute(REQUEST request, PAGEABLE pageable);
}
