package ru.nexonmi.DiaryBotNexonmi.botapi.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.nexonmi.DiaryBotNexonmi.botapi.updates.service.MyInlineKeyboardButton;


import java.util.LinkedList;
import java.util.List;

@Service
public class MessageService {
    private LocaleMessageService localeMessageService;

    public MessageService(LocaleMessageService localeMessageService) {
        this.localeMessageService = localeMessageService;
    }

    public SendMessage getReplyMessage(long chatId, String replayText) {
        return new SendMessage(String.valueOf(chatId), replayText);
    }

    public SendMessage getReplyMessage(long chatId, String replyMessageCode, InlineKeyboardMarkup keyboardMarkup) {
        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), localeMessageService.getMessage(replyMessageCode));
        sendMessage.setReplyMarkup(keyboardMarkup);
        return sendMessage;
    }

    public EditMessageText getEditMessage(long chat_id, int message_id, String replayText, InlineKeyboardMarkup keyboard) {
        EditMessageText edMes = new EditMessageText();
        edMes.setChatId(chat_id);
        edMes.setMessageId(message_id);
        edMes.setText(replayText);
        if (keyboard != null)
            edMes.setReplyMarkup(keyboard);
        return edMes;
    }

    /**
     * Creates inline keyboard.
     *
     * @param buttons array with MyInlineKeyboardButtons which must be in
     * @return keyboard for message
     */
    public InlineKeyboardMarkup getReplayKeyboardInMessage(MyInlineKeyboardButton[][] buttons) {
        //todo write checks if there is too much buttons
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new LinkedList<>();

        //start filling the keyboard with data
        for (MyInlineKeyboardButton[] line : buttons) {
            List<InlineKeyboardButton> lineBuf = new LinkedList<>();
            for (MyInlineKeyboardButton button : line) {
                if (button == null) continue;
                InlineKeyboardButton butBuf = new InlineKeyboardButton();
                butBuf.setText(button.title);
                butBuf.setCallbackData(button.callbackData);
                lineBuf.add(butBuf);
            }
            rows.add(lineBuf);
        }

        markup.setKeyboard(rows);
        return markup;
    }

    public String getSourceText(String replyMessageCode) {
        return localeMessageService.getMessage(replyMessageCode);
    }

    public String getRussianStringDay(int day) {
        return switch (day) {
            case 1 -> "Понедельник";
            case 2 -> "Вторник";
            case 3 -> "Среда";
            case 4 -> "Четверг";
            case 5 -> "Пятница";
            case 6 -> "Суббота";
            case 7 -> "Воскресенье";
            default -> "Неизвестный день";
        };
    }

    public String getRussianStringMonth(int monthNum){
        switch (monthNum){
            case 1: return "января";

            case 2: return "февраля";

            case 3: return "марта";

            case 4: return "апреля";

            case 5: return "майа";

            case 6: return "июня";

            case 7: return "июля";

            case 8: return "августа";

            case 9: return "сентября";

            case 10: return "октября";

            case 11: return "ноября";

            case 12: return "декабря";

            default: return "ошибка в получении месяца";
        }
    }


}
