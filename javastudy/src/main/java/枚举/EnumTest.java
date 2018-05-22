package 枚举;

import java.util.Scanner;

public class EnumTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a Size:SMALL(S), MEDIUM(M), LARGE(L), EXTRA_LARGE(XL)");
        String input = in.next().toUpperCase();
        Size size = Enum.valueOf(Size.class, input);
        System.out.println("size=" + size);
        System.out.println("addreviation=" + size.getAddrevition());
    }
}
