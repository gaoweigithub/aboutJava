package 垃圾回收;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class TestJConsole {
    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }

    static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }
}
