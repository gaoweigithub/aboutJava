package 异常;

import java.util.Objects;

public class ThrowEarlyCatchLater {
    public static void main(String[] args) {
        readReference(null);
    }
    public static void readReference(String name){
        Objects.requireNonNull(name);
        System.out.println(name);
    }
}
