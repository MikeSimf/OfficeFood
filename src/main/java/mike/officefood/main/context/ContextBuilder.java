package mike.officefood.main.context;

import mike.officefood.repository.entity.UserRole;
import mike.officefood.repository.entity.TelegramUser;

public class ContextBuilder {
    private TelegramUser user;
    private UserRole role;
    private String chatType;
    private String chatId;
    private String message;
    private CommandParams params = new CommandParams();

    public ContextBuilder setUser(TelegramUser user) {
        this.user = user;
        return this;
    }

    public ContextBuilder setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public ContextBuilder setChatType(String chatType) {
        this.chatType = chatType;
        return this;
    }

    public ContextBuilder setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public ContextBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public ContextBuilder addParam(String key, Object value) {
        this.params.addParam(key, value);
        return this;
    }

    public CommandContext build() {
        if (this.user != null) this.role = user.getRole();
        if (this.role == null) this.role = UserRole.ANONYMOUS;
        return new CommandContext(user, role, chatType, chatId, message, params);
    }
}
