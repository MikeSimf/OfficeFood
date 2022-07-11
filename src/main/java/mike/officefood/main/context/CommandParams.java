package mike.officefood.main.context;

import java.util.HashMap;

public class CommandParams {
    private HashMap<String, Object> params;

    public void addParam(String key, Object value) {
        params.put(key, value);
    }

    public Object getParam(String key) {
        if (params.containsKey(key)) {
            return params.get(key);
        }
        return null;
    }
}
