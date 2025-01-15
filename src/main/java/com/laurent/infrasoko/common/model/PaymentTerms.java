package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum PaymentTerms implements BaseEnum {
    IMMEDIATE("Immediate"),
    NET_15("Net 15"),
    NET_30("Net 30"),
    NET_45("Net 45"),
    NET_60("Net 60");

    private final String display;

    private PaymentTerms(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
