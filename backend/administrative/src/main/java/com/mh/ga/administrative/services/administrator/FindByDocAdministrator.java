package com.mh.ga.administrative.services.administrator;

public interface FindByDocAdministrator<REQUEST, RESPONSE> {
    RESPONSE execute(REQUEST document);
}
