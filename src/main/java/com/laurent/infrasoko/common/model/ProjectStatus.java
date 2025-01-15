package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum ProjectStatus implements BaseEnum {
    NEW("New"),
    IN_PROGRESS("In Progress"),
    ON_HOLD("On Hold"),
    UNDER_REVIEW("Under Review"),
    PENDING_APPROVAL("Pending Approval"),
    IN_DLP("In Defect Liability Period"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled"),
    ;

    private final String display;

    ProjectStatus(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
