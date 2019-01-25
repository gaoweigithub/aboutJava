package com.gw.container.common;

import com.alibaba.fastjson.JSON;
import com.gw.container.model.Header;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class ActionMapUtil {

    private static Map<String, Map<String, Action>> map = new HashMap<>();


    public static Object invoke(String service, String key, String request, Header header) throws Exception {
        if (map.containsKey(service) && map.get(service).containsKey(key)) {
            Action action = map.get(service).get(key);
            if (action != null) {
                Method method = action.getMethod();
                try {
                    //request 和header传入
                    Parameter[] parameters = method.getParameters();
                    if (parameters == null || parameters.length == 0) {
                        return method.invoke(action.getObject());
                    } else if (parameters.length == 1) {
                        if (parameters[0].getType() == Header.class) {
                            return method.invoke(action.getObject(), header);
                        } else {
                            if (StringUtils.isBlank(request)) {
                                return method.invoke(action.getObject(), null);
                            } else {
                                return method.invoke(action.getObject(), JSON.parseObject(request, parameters[0].getType()));
                            }
                        }
                    } else if (parameters.length == 2) {
                        if (parameters[0].getType() == Header.class) {
                            return method.invoke(action.getObject(), header, JSON.parseObject(request, parameters[1].getType()));
                        } else if (parameters[1].getType() == Header.class) {
                            return method.invoke(action.getObject(), JSON.parseObject(request, parameters[0].getType()), header);
                        } else {
                            return method.invoke(action.getObject());
                        }
                    } else {
                        return method.invoke(action.getObject());
                    }
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

    /**
     * 服务动作检查
     *
     * @param service
     * @param action
     * @return
     */
    public static UrlErrorCode check(String service, String action) {
        if (StringUtils.isBlank(service) && StringUtils.isBlank(action)) {
            return UrlErrorCode.MissingServiceAndActionName;
        } else if (StringUtils.isBlank(service)) {
            return UrlErrorCode.MissingServiceName;
        } else if (StringUtils.isBlank(action)) {
            return UrlErrorCode.MissingActionName;
        } else if (!map.containsKey(service)) {
            return UrlErrorCode.ServiceNotExist;
        } else if (!map.get(service).containsKey(action)) {
            return UrlErrorCode.ActionNotExist;
        } else {
            return UrlErrorCode.Ok;
        }
    }

    public enum UrlErrorCode {
        Ok(0, "正常"),
        MissingServiceName(1, "缺失服务名"),
        MissingActionName(2, "缺失动作名"),
        MissingServiceAndActionName(3, "缺失服务和动作"),
        ServiceNotExist(4, "请求的服务不存在"),
        ActionNotExist(5, "请求的动作不存在");

        private int code;
        private String message;

        UrlErrorCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getAddrevition() {
            return code;
        }
    }
}
