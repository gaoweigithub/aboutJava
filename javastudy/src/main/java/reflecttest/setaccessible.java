package reflecttest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 私有成员封装性的打破
 * 1，获取该成员的field信息  设置accessible为true
 * 2，获取该成员getXX  setXX函数进行获取和设置
 */
public class setaccessible {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Object user = Class.forName("reflecttest.user").newInstance();
        Field field = user.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(user, "ggww");

//        Method method = user.getClass().getDeclaredMethod("setName", String.class);
//        method.invoke(user,"ggww");

        System.out.println(field.get(user));
    }
}
