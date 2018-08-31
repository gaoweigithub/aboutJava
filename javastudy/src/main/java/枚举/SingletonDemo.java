package 枚举;

public enum SingletonDemo {
    Instance;
    public String getName(){
        return  "111";
    }

    public static void main(String[] args) {
        SingletonDemo ss = SingletonDemo.Instance;
        System.out.println(ss.getName());
    }
}
