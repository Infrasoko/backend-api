package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum AccessLevel implements BaseEnum {
    VIEW_ONLY("View Only"),
    EDIT("Edit"),
    ADMIN("Admin"),
    APPROVE("Approve");

    private final String display;

    AccessLevel(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
