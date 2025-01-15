package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum DocumentType implements BaseEnum {
    CONTRACT("Contract"),
    INVOICE("Invoice"),
    GUARANTEE("Guarantee"),
    QUALITY_CHECKLIST("Quality Checklist"),
    TECHNICAL_SPECIFICATION("Technical Specification"),
    DRAWING("Drawing"),
    REPORT("Report"),
    CERTIFICATE("Certificate"),
    OTHER("Other");

    private final String display;

    DocumentType(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }

}
