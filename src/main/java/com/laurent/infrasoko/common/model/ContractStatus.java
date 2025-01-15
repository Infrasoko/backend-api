package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum ContractStatus implements BaseEnum {
    DRAFT("Draft"),
    ACTIVE("Active"),
    EXPIRED("Expired"),
    TERMINATED("Terminated"),
    COMPLETE("Complete"),
    UNDER_REVIEW("Under Review");

    private final String display;

    private ContractStatus(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
