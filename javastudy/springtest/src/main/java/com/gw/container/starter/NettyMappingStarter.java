package com.gw.container.starter;

import com.gw.annotations.NettyController;
import com.gw.annotations.NettyMapping;
import com.gw.container.common.Action;
import com.gw.container.common.ActionMapUtil;
import com.gw.scanner.PackageScanner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

@StarterOrderAnnotation(order = 3)
public class NettyMappingStarter extends BaseStarter {
    @Override
    protected void _process() {
        ApplicationContext context = ContextUtil.getContext();

        new PackageScanner() {
            @Override
            public void dealClass(Class<?> klass) throws NoSuchMethodException {
                if (klass.isAnnotationPresent(NettyController.class)) {
                    NettyController controllerAnnotaion = klass.getAnnotation(NettyController.class);
                    if (controllerAnnotaion != null) {
                        String serviceName = controllerAnnotaion.value();
                        if (StringUtils.isNotBlank(serviceName) && context.containsBean(serviceName)) {
                            //bean获取
                            Object beanObj = context.getBean(serviceName);
                            Method[] methods = klass.getMethods();
                            for (Method method : methods) {
                                if (method.isAnnotationPresent(NettyMapping.class)) {
                                    //根据参数匹配出bean中对应方法
                                    Method beanMethod = beanObj.getClass().getMethod(method.getName(), method.getParameterTypes());
                                    if (beanMethod != null) {
                                        Action action = new Action();
                                        action.setMethod(beanMethod);
                                        action.setObject(beanObj);
                                        action.setMethods(method.getAnnotation(NettyMapping.class).METHODS());
                                        ActionMapUtil.put(serviceName, method.getAnnotation(NettyMapping.class).value(), action);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.packageScanner("com.gw.controller");
    }
}
