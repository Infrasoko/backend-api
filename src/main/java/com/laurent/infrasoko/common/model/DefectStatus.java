package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum DefectStatus implements BaseEnum{
    REPORTED("Reported"),
    IN_PROGRESS("In Progress"),
    RESOLVED("Resolved"),
    VERIFIED("Verified"),
    REOPENED("Reopened"),
    ;
    private final String display;

    DefectStatus(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }

}
