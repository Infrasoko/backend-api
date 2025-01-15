package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum QualityCheckStatus implements BaseEnum {
    PENDING("Pending"),
    PASSED("Passed"),
    FAILED("Failed");

    private final String dispay;

    QualityCheckStatus(String dispay) {
        this.dispay = dispay;
    }

    @Override
    public String getDisplay() {
        return dispay;
    }
}
