package 泛型;

public class GenericTest<T extends Number> {
    T foo(T t) {
        return t;
    }
}
