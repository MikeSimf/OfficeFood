package mike.officefood.lunch;

import mike.officefood.lunch.repository.entity.LunchRole;
import mike.officefood.lunch.repository.entity.LunchTelegramUser;

import java.util.HashMap;

public class OperationContext {
    private LunchTelegramUser user;
    private LunchRole role;
    private String chatType;
    private String chatId;
    private HashMap<String, Object> params;

    protected OperationContext(LunchTelegramUser user, LunchRole role, String chatType, String chatId, HashMap<String
            , Object> params) {
        this.user = user;
        this.role = role;
        this.chatType = chatType;
        this.chatId = chatId;
        this.params = params;
    }

    public LunchTelegramUser getUser() {
        return user;
    }

    public LunchRole getRole() {
        return role;
    }

    public String getChatType() {
        return chatType;
    }

    public String getChatId() {
        return chatId;
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public Object getParam(String key) {
        if (params.containsKey(key)) {
            return params.get(key);
        }
        return null;
    }
}
