package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum GuaranteeType implements BaseEnum {
    BID_BOND("Bid Bond"),
    PERFORMANCE_BOND("Performance Bond"),
    ADVANCE_PAYMENT_GUARANTEE("Advance Payment Guarantee"),
    RETENTION_MONEY_GUARANTEE("Retention Money Guarantee"),
    WARRANTY("Warranty"),
    OTHER("Other");

    private final String display;

    GuaranteeType(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
