package com.example.coachathandbot.command;

import com.example.coachathandbot.FrontList.Menu;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class StartCommandHandler extends BotCommand {
    public final Menu menu;

    public StartCommandHandler(Menu menu) {
        super("start", "");
        this.menu = menu;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        try {
            absSender.execute(menu.getStartMessage(chat));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }



    }
}
