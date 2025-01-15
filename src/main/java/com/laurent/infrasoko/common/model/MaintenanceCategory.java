package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum MaintenanceCategory implements BaseEnum {
    IT("IT"),
    ELECTRICAL("Electrical"),
    MECHANICAL("Mechanical"),
    PLUMBING("Plumbing"),
    CIVIL("Civil"),
    HVAC("Heating, Ventilation and Air Conditioning");

    private final String display;

    private MaintenanceCategory(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}

