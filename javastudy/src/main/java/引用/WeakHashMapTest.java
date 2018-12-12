package 引用;

import java.util.WeakHashMap;

public class WeakHashMapTest {
    public static void main(String[] args) {
        WeakHashMap<Integer, Integer> weakHashMap = new WeakHashMap<>();
        Integer i1 = new Integer(1231231);
        Integer i2 = new Integer(121232312);
        Integer i3 = new Integer(1231223);
        weakHashMap.put(i1, 1);
        weakHashMap.put(i2, 2);
        weakHashMap.put(i3, 3);
        weakHashMap.entrySet().stream().forEach(i -> System.out.println("key:" + i.getKey() + "value:" + i.getValue()));

        i1 = null;
        System.gc();
        System.out.println("aaaaaaa");
        weakHashMap.entrySet().stream().forEach(i -> System.out.println("key:" + i.getKey() + "value:" + i.getValue()));
    }
}
