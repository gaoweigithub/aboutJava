package 内部类.静态内部类;

import java.util.Random;

public class StaticInnerClassTest {
    public static void main(String[] args) {
        double[] d = new double[20];
        for (int i = 0; i < d.length; i++) {
            d[i] = Math.random() * 100;
        }
        ArrayAlg.Pair pair = ArrayAlg.minmax(d);
        System.out.println("min" + pair.getFirst());
        System.out.println("max" + pair.getSecond());
    }
}
