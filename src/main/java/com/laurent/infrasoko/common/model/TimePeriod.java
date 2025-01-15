package com.laurent.infrasoko.common.model;

import com.laurent.infrasoko.common.enumeration.BaseEnum;

public enum TimePeriod implements BaseEnum {
    DAY("Day"),
    WEEK("Week"),
    MONTH("Month"),
    QUARTER("Quarter"),
    YEAR("Year");

    private final String display;

    TimePeriod(String display) {
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
