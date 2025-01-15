package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum BidStatus implements BaseEnum {
    DRAFT("Draft"),
    PUBLISHED("Published"),
    UNDER_EVALUATION("Under Evaluation"),
    AWARDED("Awarded"),
    REJECTED("Rejected"),
    CANCELLED("Cancelled"),
    EXPIRED("Expired");

    private final String display;

    private BidStatus(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
