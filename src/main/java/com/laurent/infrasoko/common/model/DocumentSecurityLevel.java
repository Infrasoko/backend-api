package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum DocumentSecurityLevel implements BaseEnum {
    PUBLIC("Public"),
    CONFIDENTIAL("Confidential"),
    RESTRICTED("Restricted");

    private final String display;

    private DocumentSecurityLevel(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
