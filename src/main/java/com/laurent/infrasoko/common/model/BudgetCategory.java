package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum BudgetCategory implements BaseEnum {
    MATERIALS("Materials"),
    LABOUR("Labour"),
    EQUIPMENT("Equipment"),
    FUEL("Fuel"),
    OVERHEADS("Overheads");

    private final String display;

    private BudgetCategory(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
