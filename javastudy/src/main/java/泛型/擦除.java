package 泛型;

public class 擦除 {
    public static void main(String[] args) {
        GenericTest<Integer> gg = new GenericTest<>();
        Integer dd = gg.foo(123);
        System.out.println(dd);
    }
}
