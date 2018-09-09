package reflecttest.testExtend;

public class TestClassExted extends testClass {
    public static void main(String[] args) {
        testClass tt = new testClass();
        System.out.println(tt.getClass().isAssignableFrom(TestClassExted.class));
    }
}
