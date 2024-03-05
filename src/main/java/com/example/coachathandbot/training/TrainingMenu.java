package com.example.coachathandbot.training;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.coachathandbot.training.Training.*;

public class TrainingMenu {
    public static InlineKeyboardMarkup getAllTrainings() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(createButtonRow(BACK_AND_TRICEPS));
        rowsInline.add(createButtonRow(CHEST_AND_LEGS));
        rowsInline.add(createButtonRow(CHEST_AND_BICEPS));

        rowsInline.add(createButtonRow(BACK_AND_SHOULDERS));
        rowsInline.add(createButtonRow(LEGS_AND_BICEPS));
        rowsInline.add(createButtonRow(CHEST_AND_TRICEPS));

        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    private static List<InlineKeyboardButton> createButtonRow(Training... trainings) {
        return Arrays.stream(trainings).map(TrainingMenu::createButton).toList();
    }

    private static InlineKeyboardButton createButton(Training trainings) {
        var button = new InlineKeyboardButton();
        button.setText(trainings.getName());
        button.setCallbackData(trainings.getCallback());
        return button;
    }
}
