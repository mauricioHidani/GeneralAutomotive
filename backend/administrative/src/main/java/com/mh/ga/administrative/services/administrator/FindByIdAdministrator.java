package com.mh.ga.administrative.services.administrator;

public interface FindByIdAdministrator<REQUEST, RESPONSE> {
    RESPONSE execute(REQUEST id);
}
