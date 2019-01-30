package com.gw.scanner;

import com.gw.annotations.NettyController;
import com.gw.annotations.NettyMapping;
import com.gw.container.common.Action;
import com.gw.container.common.ActionMapUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;

@Deprecated
public class PostProcessor implements BeanPostProcessor {

    public void init(){

    }
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //代理无法支持mapping
        //一层一层获取superrclass
        Class realClass = bean.getClass();
        boolean add = false;
        while (realClass != Object.class) {
            if (realClass.isAnnotationPresent(NettyController.class)) {
                add = true;
                break;
            }
            realClass = realClass.getSuperclass();
        }

        if (add) {
            Method[] methods = new Method[0];
            try {
                methods = bean.getClass().getMethods();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Method method : methods) {
                //获取真实method信息


                NettyMapping actionMap = method.getAnnotation(NettyMapping.class);
                if (actionMap != null) {
                    Action action = new Action();
                    action.setMethod(method);
                    action.setObject(bean);
                    action.setMethods(actionMap.METHODS());
                    ActionMapUtil.put(beanName, actionMap.value(), action);
                }
            }
        }
        return bean;
    }
}
