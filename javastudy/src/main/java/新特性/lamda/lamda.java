package 新特性.lamda;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

/**
 * lamda引用外部变量必须为既成事实的final
 */
public class lamda {
    public static void main(String[] args) {
        String name = "aa";
//        name ="aa";
        Stream<Object> stream = Stream.of(1, 2, 3, 4);
        stream.forEach(i -> System.out.println(name + i));
//
//        ConcurrentHashMap concurrentMap = new ConcurrentHashMap();
//        concurrentMap.put(1,2);
//        concurrentMap.put(2,2);
//        concurrentMap.put(3,2);
//        concurrentMap.put(4,2);
//        concurrentMap.size();
//        concurrentMap.mappingCount();


        HashMap hs = new HashMap(5);
        System.out.println(hs.size());
    }
}
