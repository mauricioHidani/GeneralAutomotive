package com.mh.gaimadministrative.models.enums;

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
        throw new IllegalArgumentException("The information for type Office validation is not valid");
    }

}
