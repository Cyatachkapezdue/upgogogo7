package com.example.coachathandbot.config;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;

@Component
public class TelegramBotCommandInitializer implements InitializingBean {
    private final ICommandRegistry iCommandRegistry;
    private final IBotCommand[] commands;

    public TelegramBotCommandInitializer(ICommandRegistry iCommandRegistry, IBotCommand... commands) {
        this.iCommandRegistry = iCommandRegistry;
        this.commands = commands;
    }

    @Override
    public void afterPropertiesSet() {
        iCommandRegistry.registerAll(commands);
    }
}
