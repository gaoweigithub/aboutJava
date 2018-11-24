package json;

public class bytetest {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(6));
        char[] dd = Integer.toBinaryString(7).toCharArray();
        System.out.println(dd);
        int sum = 0;
        for (char c : dd){
            sum += Integer.valueOf(String.valueOf(c));
        }
        System.out.println(sum);
    }
}
