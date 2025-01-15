package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum ProjectPriority implements BaseEnum {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    CRITICAL("Critical");

    private final String display;

    ProjectPriority(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
