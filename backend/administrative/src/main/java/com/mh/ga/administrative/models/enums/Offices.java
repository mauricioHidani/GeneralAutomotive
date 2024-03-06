package com.mh.ga.administrative.models.enums;

public enum Offices {

    BUYING_COORDINATOR,
    LOGISTICS_ADMINISTRATOR,
    DEMAND_PLANNING_SPECIALIST;

    public static Offices toEnum(String str) {
        for (Offices office : Offices.values()) {
            if (office.toString().equals(str)) {
                return office;
            }
        }
        return null;
    }

}
