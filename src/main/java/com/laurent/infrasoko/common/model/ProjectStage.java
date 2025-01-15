package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum ProjectStage implements BaseEnum {
    PREMINALIRES("Préliminaires"),
    DESIGN("Design"),
    ENGINEERING("Engineering"),
    PROCUREMENT("Procurement"),
    MOBILIZATION("Mobilization"),
    CONSTRUCTION("Construction"),
    TESTING_AND_COMMISSIONING("Testing and Commissioning"),
    HANDOVER("Handover");

    private final String display;

    ProjectStage(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
