package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum MaterialRequestStatus implements BaseEnum {
    DRAFT("Draft"),
    SUBMITTED("Submitted"),
    UNDER_REVIEW("Under Review"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    PARTIALLY_DELIVERED("Partially Delivered"),
    FULLY_DELIVERED("Fully Delivered");

    private final String display;

    MaterialRequestStatus(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
