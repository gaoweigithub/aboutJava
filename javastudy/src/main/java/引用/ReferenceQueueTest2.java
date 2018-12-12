package 引用;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ReferenceQueueTest2 {
    public static void main(String[] args) {
        Object value = new Object();
        Map<Object, Object> map = new HashMap<>();
        ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();

        Thread thread = new Thread(() -> {
            try {
                int cnt = 0;
                WeakReference<byte[]> k;
                while((k = (WeakReference) referenceQueue.remove()) != null) {
                    System.out.println((cnt++) + "回收了:" + k);
                }
            } catch(InterruptedException e) {
                //结束循环
            }
        });
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 300; i++) {
            int _1M = 1024 * 1024 * 1024;
            byte[] bytes = new byte[_1M];
            WeakReference<byte[]> weakReference = new WeakReference<>(bytes, referenceQueue);
            map.put(weakReference, value);
        }
        System.out.println("map.size->" + map.size());
    }
}
