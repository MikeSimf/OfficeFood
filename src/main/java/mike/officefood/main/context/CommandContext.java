package mike.officefood.main.context;

import mike.officefood.repository.entity.UserRole;
import mike.officefood.repository.entity.TelegramUser;

public class CommandContext {
    private TelegramUser user;
    private UserRole role;
    private String chatType;
    private String chatId;
    private String message;
    private CommandParams params;

    protected CommandContext(TelegramUser user, UserRole role, String chatType, String chatId,
            String message, CommandParams params) {
        this.user = user;
        this.role = role;
        this.chatType = chatType;
        this.chatId = chatId;
        this.message = message;
        this.params = params;
    }

    public TelegramUser getUser() {
        return user;
    }

    public UserRole getRole() {
        return role;
    }

    public String getChatType() {
        return chatType;
    }

    public String getChatId() {
        return chatId;
    }

    public String getMessage() {
        return message;
    }

    public CommandParams getParams() {
        return params;
    }

    public Object getParam(String key) {
        return params.getParam(key);
    }
}
