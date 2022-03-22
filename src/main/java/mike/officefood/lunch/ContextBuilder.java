package mike.officefood.lunch;

import mike.officefood.lunch.repository.entity.LunchRole;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;

import java.util.HashMap;

public class ContextBuilder {
    private LunchTelegramUser user;
    private LunchRole role;
    private String chatType;
    private String chatId;
    private HashMap<String, Object> params = new HashMap<>();

    public ContextBuilder setUser(LunchTelegramUser user) {
        this.user = user;
        return this;
    }

    public ContextBuilder setRole(LunchRole role) {
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

    public ContextBuilder addParam(String key, Object value) {
        this.params.put(key, value);
        return this;
    }

    public OperationContext build() {
        if (this.user != null) this.role = user.getRole();
        if (this.role == null) this.role = LunchRole.LUNCH_ANONYMOUS;
        return new OperationContext(user, role, chatType, chatId, params);
    }
}
