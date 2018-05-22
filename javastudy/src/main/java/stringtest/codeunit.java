package stringtest;

/**
 * 代码点和代码单元
 */
public class codeunit {
    public static void main(String[] args) {
        String ss = "Ƶ123";
        System.out.println(ss.toCharArray()[0]);
        System.out.println(ss.charAt(0));
        System.out.println("length:" + ss.length());
        System.out.println("cplength:" + ss.codePointCount(0, ss.length()));
    }
}
