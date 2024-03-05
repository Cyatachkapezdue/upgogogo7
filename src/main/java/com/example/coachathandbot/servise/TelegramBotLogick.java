package com.example.coachathandbot.servise;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component

public class TelegramBotLogick extends TelegramLongPollingCommandBot {

    private final String username;

    public TelegramBotLogick(@Value("${bot.token}") String botToken, @Value("${bot.username}") String username) {
        super(botToken);
        this.username = username;
    }



    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void processNonCommandUpdate(Update update) {

        if(update.getCallbackQuery().getData().);


        try {
            execute(SendMessage.builder()
                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                    .text(".....")
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);

        }
        try {
            sendApiMethod(AnswerCallbackQuery.builder()
                    .callbackQueryId(update.getCallbackQuery().getId())
                    .text("").build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }


    }
}