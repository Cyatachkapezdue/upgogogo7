package com.example.coachathandbot.servise;

import com.example.coachathandbot.training.Training;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

import static com.example.coachathandbot.FrontList.Action.GYM;
import static com.example.coachathandbot.training.TrainingMenu.getAllTrainings;

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
        if (update.hasCallbackQuery()) {
            var callbackQuery = update.getCallbackQuery();
            if (callbackQuery.getData().equals(GYM.toString())) {
                try {
                    execute(SendMessage.builder()
                                       .chatId(callbackQuery.getMessage().getChatId())
                                       .text("Виберіть тип тренування який потрібен")
                                       .replyMarkup(getAllTrainings())
                                       .build());
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

            Optional<Training> opening = getTrainings(callbackQuery.getData());
            opening.ifPresent(openings -> processTranings(openings, callbackQuery));

            try {
                sendApiMethod(AnswerCallbackQuery.builder()
                                                 .callbackQueryId(update.getCallbackQuery().getId())
                                                 .text("").build());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void processTranings(Training trainings, CallbackQuery callbackQuery) {
        String path = getTrainingVideoPath(trainings.getCallback());
        File videoFile = new File(path);
        String caption = trainings.getName();
        InputFile inputFile = new InputFile(videoFile);
        SendVideo sendVideo = new SendVideo();
        sendVideo.setChatId(callbackQuery.getMessage().getChatId());
        sendVideo.setVideo(inputFile);
        sendVideo.setCaption(caption);
        try {
            execute(sendVideo);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<Training> getTrainings(String callback) {
        return Arrays.stream(Training.values()).filter(o -> o.getCallback().equals(callback)).findAny();
    }

    private String getTrainingVideoPath(String opening) {
        return "src/main/resources/trainings-videos/" + opening + ".mp4";
    }

}
