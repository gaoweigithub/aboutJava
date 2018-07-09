package reflecttest;

public class classloadtest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class c = Class.forName("reflecttest.user");
        System.out.println(c == null);


        Class c2 = classloadtest.class.getClassLoader().loadClass("reflecttest.user");
        System.out.println(c2 == null);
    }
}
