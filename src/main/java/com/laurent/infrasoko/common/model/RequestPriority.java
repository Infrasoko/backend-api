package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum RequestPriority implements BaseEnum {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String display;

    RequestPriority(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
