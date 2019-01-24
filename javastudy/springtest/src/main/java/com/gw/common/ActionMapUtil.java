package com.gw.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ActionMapUtil {

    private static Map<String, Map<String, Action>> map = new HashMap<>();

    public static Object invoke(String service, String key, Object... args) throws Exception {
        if (map.containsKey(service) && map.get(service).containsKey(key)) {

            Action action = map.get(service).get(key);
            if (action != null) {
                Method method = action.getMethod();
                try {
                    return method.invoke(action.getObject(), args);
                } catch (Exception e) {
                    throw e;
                }
            }
        }
        return null;
    }

    public static void put(String service, String key, Action action) {
        if (!map.containsKey(service)) {
            map.put(service, new HashMap<>());
        }
        if (!map.get(service).containsKey(key)) {
            map.get(service).put(key, action);
        }
    }
}
