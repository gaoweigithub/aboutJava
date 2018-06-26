package 内存溢出.java堆溢出;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
    static class HeapOOMObject{}

    public static void main(String[] args) {
        List<HeapOOMObject> list = new ArrayList<>();
        while (true){
            list.add(new HeapOOMObject());
        }
    }
}
