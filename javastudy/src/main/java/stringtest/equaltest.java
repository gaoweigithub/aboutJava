package stringtest;

public class equaltest {
    public static void main(String[] args) {
        /**
         * 一定不能用==去检测两个字符串是否相等 ！！1
         * 使用equals或者compareto去检查两个字符串是否相等
         */
        System.out.println("123" == "123");
        System.out.println(("123" + 123) == "123123");
        System.out.println(("123" + 123).equals("123123"));
        System.out.println("hello".substring(0, 3));
        System.out.println("hello".substring(0, 3) == "hel");
        System.out.println(("123" + "abc") == ("123a" + "bc"));
    }
}
