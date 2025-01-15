package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum UserRoleType implements BaseEnum {
    ADMIN("Admin"),
    CLIENT("Client"),
    CONSULTANT("Consultant"),
    CONTRACTOR("Contractor");

    private final String display;

    UserRoleType(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
