import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class hahah {
    public static void main(String[] args) {

        Map<Integer, Integer> mapAllocate = new HashMap<>();
        mapAllocate.put(1, 0);
        mapAllocate.put(2, 0);
        mapAllocate.put(3, 0);
        mapAllocate.put(4, 2);
        mapAllocate.put(5, 3);
        int toProcess = 22;
        int totals = toProcess + mapAllocate.values().stream().reduce((integer, integer2) -> integer + integer2).get();
        int avg = totals / mapAllocate.size();
        int left = totals - avg * mapAllocate.size();
        System.out.println("总待分配单量:" + toProcess);
        System.out.println("总单量:" + totals);
        System.out.println("平均单量:" + avg);
        System.out.println("剩余单量:" + left);

        for (Map.Entry<Integer, Integer> entry : mapAllocate.entrySet()) {
            if (entry.getValue() < 3) {
                if (left-- > 0) {
                    System.out.println(String.format("工号：%s         现有单数:%s     缺:%s    分配：%s   总单数:%s", entry.getKey(), entry.getValue(), avg - entry.getValue(), avg - entry.getValue() + 1, entry.getValue() + avg - entry.getValue() + 1));
                } else {
                    System.out.println(String.format("工号：%s       现有单数:%s     缺:%s    分配：%s   总单数:%s", entry.getKey(), entry.getValue(), avg - entry.getValue(), avg - entry.getValue(), entry.getValue() + avg - entry.getValue()));
                }
            } else {
                System.out.println(String.format("工号：%s已饱和      现有单数:%s     缺:%s    分配：%s   总单数:%s", entry.getKey(), entry.getValue(), avg - entry.getValue(), avg - entry.getValue(), entry.getValue() + avg - entry.getValue()));
            }
        }


    }
}

//Mon Nov 26 11:51:18 CST 2018

//Mon Nov 26 11:51:33 CST 2018