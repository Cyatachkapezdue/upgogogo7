package com.example.coachathandbot.training;

import lombok.Getter;

@Getter
public enum Training {
    BACK_AND_TRICEPS("Спина + Тріцепс", "BACK_AND_TRICEPS"),
    CHEST_AND_LEGS("Ноги + Плечі", "BACK_AND_TRICEPS"),
    CHEST_AND_BICEPS("Груди + Біцепс", "BACK_AND_TRICEPS"),
    BACK_AND_SHOULDERS("Спина + Плечі", "BACK_AND_TRICEPS"),
    LEGS_AND_BICEPS("Ноги + Біцепс", "BACK_AND_TRICEPS"),
    CHEST_AND_TRICEPS("Груди + Тріцепс", "BACK_AND_TRICEPS");

    private String name;
    private String callback;

    Training(String name, String callback) {
        this.name = name;
        this.callback = callback;
    }
}
