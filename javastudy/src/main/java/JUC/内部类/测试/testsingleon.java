package JUC.内部类.测试;

public class testsingleon {
    private static testsingleon ourInstance = new testsingleon();

    public static testsingleon getInstance() {
        return ourInstance;
    }

    private testsingleon() {
    }
}
