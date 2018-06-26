package 内存溢出.方法区和运行时常量池溢出;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk1.6会爆oom    1.7之后不会   因为方法区已经放到堆里了
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        int i = 1;
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, args);
                }
            });
            enhancer.create();
            System.out.println("createed" + i++);
        }
    }

    static class OOMObject {

    }
}
