package com.mh.ga.administrative.services.administrator;

import org.springframework.data.domain.Pageable;

public interface FindByOfficeAdministrator<REQUEST, RESPONSE> {
    RESPONSE execute(REQUEST request, Pageable pageable);
}
