package JUC.test;

import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {
    public static void main(String[] args) {

        ConcurrentHashMap<String, Integer> chs = new ConcurrentHashMap<>();

        System.out.println("Aa".hashCode());
        System.out.println("BB".hashCode());

        chs.put("Aa",1);
        chs.put("BB",2);

//        chs.put("Aa",1000);
//        chs.put("1",1000);

        System.out.println(chs.get("Aa"));
        System.out.println(chs.get("BB"));
    }
}
