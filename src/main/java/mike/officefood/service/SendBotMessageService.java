package mike.officefood.service;

public interface SendBotMessageService {
    Integer sendMessage(String chatId, String message);

    Boolean editMessage(Integer messageId, String chatId, String newMessage);

    Boolean removeMessage(Integer messageId, String chatId);
}
