package CglibTest;

import net.sf.cglib.beans.*;
import net.sf.cglib.core.Converter;
import net.sf.cglib.core.KeyFactory;
import net.sf.cglib.proxy.*;
import net.sf.cglib.reflect.MethodDelegate;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.UnexpectedException;

public class unittest {
    @Test
    public void testFixedValue() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "hello cglib";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null));
        System.out.println(proxy.toString());
        System.out.println(proxy.getClass());
        /**
         * 上述代码中，FixedValue用来对所有拦截的方法返回相同的值，从输出我们可以看出来，Enhancer对非final方法test()、toString()、hashCode()进行了拦截，没有对getClass进行拦截。由于hashCode()方法需要返回一个Number，
         * 但是我们返回的是一个String，这解释了上面的程序中为什么会抛出异常。
         */
        System.out.println(proxy.hashCode());
    }

    @Test
    public void testInvocationHandler() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "hello cglib";
                } else {
                    throw new RuntimeException("do not know what to do");
                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null));
        System.out.println(proxy.toString());
        /**
         * 可以使用一个InvocationHandler(如果对InvocationHandler不懂，可以参考这里)作为回调，
         * 使用invoke方法来替换直接访问类的方法，但是你必须注意死循环。因为invoke中调用的任何原代理类方法，
         * 均会重新代理到invoke方法中。
         * 为了避免这种死循环，我们可以使用MethodInterceptor，
         * MethodInterceptor的例子在前面的hello world中已经介绍过了，这里就不浪费时间了。
         *
         */
    }

    @Test
    public void testCallBackFilter() {
        Enhancer enhancer = new Enhancer();
        CallbackHelper callbackHelper = new CallbackHelper(SampleClass.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return new FixedValue() {
                        @Override
                        public Object loadObject() throws Exception {
                            return "hello cglib";
                        }
                    };
                } else {
                    return NoOp.INSTANCE;
                }
            }
        };
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null));
        System.out.println(proxy.toString());
        System.out.println(proxy.hashCode());
    }

    @Test
    public void testImmutableBean() {
        /**
         * 不可变bean只能通过底层的类去修改，不能通过代理的不可变类修改，否则会报错
         *
         * 通过名字就可以知道，不可变的Bean。ImmutableBean允许创建一个原来对象的包装类，
         * 这个包装类是不可变的，任何改变底层对象的包装类操作都会抛出IllegalStateException。
         * 但是我们可以通过直接操作底层对象来改变包装类对象。这有点类似于Guava中的不可变视图
         *
         */
        SampleBean bean = new SampleBean();
        bean.setValue("hello world");
        SampleBean immutableBean = (SampleBean) ImmutableBean.create(bean);
        System.out.println(immutableBean.getValue() == "hello world");
        bean.setValue("hello world again");
        System.out.println(immutableBean.getValue() == "hello world again");

        /**
         * 此处报错，不能通过代理类修改
         */
        immutableBean.setValue("hello cglib");
    }

    /**
     * 手动创建一个bean
     * Bean generator
     * cglib提供的一个操作bean的工具，使用它能够在运行时动态的创建一个bean。
     *
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testBeanGenerator() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("value", String.class);
        Object myBean = beanGenerator.create();
        Method setter = myBean.getClass().getMethod("setValue", String.class);
        setter.invoke(myBean, "hello cglib");
        Method getter = myBean.getClass().getMethod("getValue");
        System.out.println("value :" + getter.invoke(myBean));
    }

    /**
     * Bean Copier
     * cglib提供的能够从一个bean复制到另一个bean中，而且其还提供了一个转换器，用来在转换的时候对bean的属性进行操作
     */
    @Test
    public void testBeanCopier() {
        BeanCopier beanCopier = BeanCopier.create(SampleBean.class, OtherSampleBean.class, false);
        SampleBean myBean = new SampleBean();
        myBean.setValue("hello cglib");
        OtherSampleBean otherBean = new OtherSampleBean();
        beanCopier.copy(myBean, otherBean, null);
        System.out.println(otherBean.getValue() == "hello cglib");
    }

    /**
     * BulkBean
     * 相比于BeanCopier，BulkBean将copy的动作拆分为getPropertyValues和setPropertyValues两个方法，
     * 允许自定义处理属性
     * <p>
     * 使用注意：
     * 1. 避免每次进行BulkBean.create创建对象，一般将其声明为static的
     * 2. 应用场景：针对特定属性的get,set操作，一般适用通过xml配置注入和注出的属性，
     * 运行时才确定处理的Source,Target类，只需要关注属性名即可。
     */
    @Test
    public void testBulkBean() {
        BulkBean bulkBean = BulkBean.create(SampleBean.class,
                new String[]{"getValue"},
                new String[]{"setValue"},
                new Class[]{String.class});
        SampleBean bean = new SampleBean();
        bean.setValue("hello cglib");
        Object[] propertyValues = bulkBean.getPropertyValues(bean);
        System.out.println(bulkBean.getPropertyValues(bean).length);
        System.out.println(bulkBean.getPropertyValues(bean)[0] == "hello cglib");
        bulkBean.setPropertyValues(bean, new Object[]{"hello world"});
        System.out.println(bulkBean.getPropertyValues(bean)[0] == "hello world");
    }


    /**
     * BeanMap
     * BeanMap类实现了Java Map，将一个bean对象中的所有属性转换为一个String-to-Obejct的Java Map
     * 我们使用BeanGenerator生成了一个含有两个属性的Java Bean，对其进行赋值操作后，生成了一个BeanMap对象，通过获取值来进行验证
     *
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testBeanMap() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BeanGenerator generator = new BeanGenerator();
        generator.addProperty("username", String.class);
        generator.addProperty("password", String.class);
        Object bean = generator.create();
        Method setUserName = bean.getClass().getMethod("setUsername", String.class);
        Method setPassword = bean.getClass().getMethod("setPassword", String.class);
        setUserName.invoke(bean, "admin");
        setPassword.invoke(bean, "password");
        BeanMap map = BeanMap.create(bean);
        System.out.println(map.get("username"));
        System.out.println(map.get("password"));
    }


    /**
     * keyFactory
     * keyFactory类用来动态生成接口的实例，接口需要只包含一个newInstance方法，返回一个Object。
     * keyFactory为构造出来的实例动态生成了Object.equals和Object.hashCode方法，能够确保相同的参数构造出的实例为单例的。
     */
    @Test
    public void testKeyFactory() {
        SampleKeyFactory keyFactory = (SampleKeyFactory) KeyFactory.create(SampleKeyFactory.class);
        Object key = keyFactory.newInstance("foo", 42);
        Object key2 = keyFactory.newInstance("foo", 42);
        System.out.println(key.equals(key2));
    }


    /**
     * Mixin(混合)
     * Mixin能够让我们将多个对象整合到一个对象中去，前提是这些对象必须是接口的实现。可能这样说比较晦涩，以代码为例：
     * Mixin类比较尴尬，因为他要求Minix的类（例如MixinInterface）实现一些接口。
     * 既然被Minix的类已经实现了相应的接口，那么我就直接可以通过纯Java的方式实现，没有必要使用Minix类。
     */
    @Test
    public void testMixin(){
        Mixin mixin = Mixin.create(new Class[]{MixinInterfaceTest.Interface1.class,
                MixinInterfaceTest.Interface2.class,MixinInterfaceTest.MixinInterface.class},
                new Object[]{new MixinInterfaceTest.Class1(),new MixinInterfaceTest.Class2()});
        MixinInterfaceTest.MixinInterface mixinDelegate = (MixinInterfaceTest.MixinInterface) mixin;

        System.out.println(mixinDelegate.first());
        System.out.println(mixinDelegate.second());
    }


    /**
     * Method delegate
     * MethodDelegate主要用来对方法进行代理
     *
     * 关于Method.create的参数说明：
     * 1. 第二个参数为即将被代理的方法
     * 2. 第一个参数必须是一个无参数构造的bean。因此MethodDelegate.create并不是你想象的那么有用
     * 3. 第三个参数为只含有一个方法的接口。当这个接口中的方法被调用的时候，
     * 将会调用第一个参数所指向bean的第二个参数方法
     *
     * 缺点：
     * 1. 为每一个代理类创建了一个新的类，这样可能会占用大量的永久代堆内存
     * 2. 你不能代理需要参数的方法
     * 3. 如果你定义的接口中的方法需要参数，那么代理将不会工作，并且也不会抛出异常；
     * 如果你的接口中方法需要其他的返回类型，那么将抛出IllegalArgumentException
     */
    @Test
    public void testMethodDelegate(){
        SampleBean bean = new SampleBean();
        bean.setValue("hello cglib");
        BeanDelegate delegate = (BeanDelegate) MethodDelegate.create(bean,"getValue",BeanDelegate.class);
        System.out.println(delegate.getValueFromDelegate());
    }
}