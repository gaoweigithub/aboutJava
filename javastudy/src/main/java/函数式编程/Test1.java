package 函数式编程;

import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("I", "Love", "you", "too");
        stream.forEach(str -> System.out.println(str));
    }
}
