package com.example.coachathandbot.FrontList;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class Menu {
    private final String GREETING_MESSAGE = """
             Про що цей бот ?
                        
             Цей бот про те, як чітко яскраво, граючи, результативно, по - кайфу отримати, досягнути: 
             
             - Працездатного
             - Здорового
             - Функціольного
             - Спортивного
              Тіла
               
            БЕЗ : Самозмушення, Самонадривання, Самобічевання, Дисципліни й схожої на це все єбали
                       
             Нище ви зможете озайомитись із покроковим алгоритмом дій які ви за власним бажанням, факультативно, так як вам зручно, так як вам комфортно,зможете інтегрувати у власне повсякдення і черпати приємні відчуття, 
             враження від власного результату.
                        
             P.S Без прикладання зусиль і праці над собою досягти бажаного буде магією, а тут її нема тут аналіз даних, алгоритміка, дослідження,факти і практика.
                     
              
              """;

    public SendMessage getStartMessage(Chat chat) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(List.of(createButton("Система генерації бажання", Action.MOTIVATION.name(), "")));
        rowsInline.add(List.of(createButton("Сон", Action.SLEEP.name(), "")));
        rowsInline.add(List.of(createButton("Харчування", Action.MEAL.name(), "")));
        rowsInline.add(List.of(createButton("Тренування", Action.GYM.name(), "")));
        markupInline.setKeyboard(rowsInline);
        SendMessage message = new SendMessage();
        message.setChatId(chat.getId());
        message.setText(GREETING_MESSAGE);
        message.setReplyMarkup(markupInline);
        return message;
    }

    private static InlineKeyboardButton createButton(String title, String callback, String url) {
        var button = new InlineKeyboardButton();
        button.setText(title);
        button.setUrl(url);
        button.setCallbackData(callback);
        return button;
    }
}
