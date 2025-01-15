package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum NotificationType implements BaseEnum {
    CONTRACT_TYPE("Contract Type"),
    GUARANTEE_EXPIRY("Guarantee Expiry"),
    BUDGET_OVERRUN("Budget Overrun"),
    MILESTONE_DEADLINE("Milestone Deadline"),
    MATERIAL_DELIVERY("Material Delivery"),
    QUALITY_ISSUE("Quality Issue"),
    PAYMENT_DUE("Payment Due"),
    MAINTENANCE_ALERT("Maintenance Alert");

    private final String display;

    private NotificationType(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
