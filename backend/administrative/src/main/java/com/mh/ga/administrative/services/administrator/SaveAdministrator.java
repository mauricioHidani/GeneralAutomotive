package com.mh.ga.administrative.services.administrator;

public interface SaveAdministrator<REQUEST, RESPONSE> {
    RESPONSE execute(REQUEST request);
}
