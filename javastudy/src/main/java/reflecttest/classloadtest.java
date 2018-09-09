package reflecttest;

public class classloadtest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName("reflecttest.user");
        Object obj = c.newInstance();
        System.out.println(obj == null);


        Class c2 = classloadtest.class.getClassLoader().loadClass("reflecttest.user2");
        Object obj2 = c2.newInstance();
        System.out.println(obj2 == null);
    }
}
