package com.gw.scanner;

import com.gw.annotations.NettyMapping;
import com.gw.common.Action;
import com.gw.common.ActionMapUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;

public class PostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof NettyBeanScannerConfigurer.FactoryBeanTest){
            Method[] methods= new Method[0];
            try {
                methods = ((NettyBeanScannerConfigurer.FactoryBeanTest)bean).getObject().getClass().getMethods();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Method method : methods) {
                NettyMapping actionMap=method.getAnnotation(NettyMapping.class);
                if(actionMap!=null){
                    Action action=new Action();
                    action.setMethod(method);
                    action.setObject(bean);
                    action.setMethods(actionMap.METHODS());
                    ActionMapUtil.put(actionMap.value(), action);
                }
            }
        }
        return bean;
    }
}
