package com.agvicente.gptprojectdemo.model.enums;

import java.security.InvalidParameterException;
import java.util.Arrays;

public enum InteractionTypeEnum {

    INIT_CONVERSATION("init-conversation"),
    T_SHIRT("t-shirt"),
    PANTS("pants"),
    SHOES("shoes"),
    MEASURES("measures"),
    STYLE("style"),
    FEEDBACK("feedback");

    private String description;
    private InteractionTypeEnum(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public static InteractionTypeEnum getByDescription(String description) {
        InteractionTypeEnum interactionType =  Arrays.stream(InteractionTypeEnum.values())
                .filter(i -> i.getDescription().equals(description))
                .findFirst()
                .orElseThrow();
        return interactionType;
    }
}
