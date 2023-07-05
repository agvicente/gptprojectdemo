package com.agvicente.gptprojectdemo.model.enums;

import java.security.InvalidParameterException;
import java.util.Arrays;

public enum InteractionTypeEnum {

    /**
     * A ideia era criar uma máquina de estados finitos para gerenciar este fluxo
     */
    INIT_CONVERSATION(1,"init-conversation"),
    T_SHIRT(2, "t-shirt"),
    PANTS(3, "pants"),
    SHOES(4, "shoes"),
    MEASURES(5, "measures"),
    STYLE(6, "style"),
    FEEDBACK(7, "feedback"),
    GENERATE_PROMPT(8, "generate-prompt");

    private int code;
    private String description;

    private InteractionTypeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    public static InteractionTypeEnum getByDescription(String description) {
        InteractionTypeEnum interactionType =  Arrays.stream(InteractionTypeEnum.values())
                .filter(i -> i.getDescription().equals(description))
                .findFirst()
                .orElseThrow();
        return interactionType;
    }

    public static InteractionTypeEnum getByCode(int code) {
        InteractionTypeEnum interactionType =  Arrays.stream(InteractionTypeEnum.values())
                .filter(i -> i.getCode() == code)
                .findFirst()
                .orElseThrow();
        return interactionType;
    }
}
