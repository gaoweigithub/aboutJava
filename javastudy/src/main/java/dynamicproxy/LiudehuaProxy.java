/**
 * @(#)LiudehuaProxy.java Created by gw33973 on 2018/4/12   0:03
 * <p>
 * Copyrights (C) 2018保留所有权利
 */

package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * (类型功能说明描述)
 * <p>
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2018/4/12 0:03   gw33973     1.0       初始化创建<br>
 * </p>
 *
 * @author gw33973
 * @version 1.0
 * @since JDK1.7
 */
public class LiudehuaProxy {
    private Person ldh = new Liudehua();

    public Person getProxy() {
        return (Person) Proxy.newProxyInstance(LiudehuaProxy.class.getClassLoader(), ldh.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("sing")) {
                            System.out.println("我是他经纪人，要他唱歌先给十万");
                            return method.invoke(ldh, args);
                        }
                        if (method.getName().equals("dance")) {
                            System.out.println("要他跳舞先给二十万");
                            return method.invoke(ldh, args);
                        }
                        return null;
                    }
                });
    }
}
