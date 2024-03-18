package com.mh.ga.administrative.services.administrator;

public interface UpdateAdministrator<IDENTITY, UPDATE, RESPONSE> {
    RESPONSE execute(IDENTITY identity, UPDATE update);
}
