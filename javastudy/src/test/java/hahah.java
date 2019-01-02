import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class hahah {
    public static void main(String[] args) {
        List<Integer> ll = new ArrayList<>();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll = ll.stream().filter(i->i>3).collect(Collectors.toList());
        ll.forEach(i-> System.out.println(i));
    }
}

//Mon Nov 26 11:51:18 CST 2018

//Mon Nov 26 11:51:33 CST 2018