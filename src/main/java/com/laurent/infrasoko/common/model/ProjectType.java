package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum ProjectType implements BaseEnum {
    CAPITAL("Capital"),
    MAINTENANCE("Maintenance");

    private final String display;

    ProjectType(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
