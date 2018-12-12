package 引用;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class WeakReferenceCache {
    public static void main(String[] args) {
        final Map<Object, WeakR> hashMap = new HashMap<>();
        int _1M = 1024 * 1024;
        ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();

        Thread thread = new Thread(() -> {
            try {
                int cnt = 0;
                WeakR k;
                while ((k = (WeakR) referenceQueue.remove()) != null) {
                    System.out.println((cnt++) + "回收了:" + k);
                    //触发反向hash remove
                    hashMap.remove(k.key);
                    //额外对key对象作其它处理，比如关闭流，通知操作等
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        for (int i = 0; i < 10000; i++) {
            byte[] byteKey = new byte[_1M];
            byte[] byteValue = new byte[_1M];
            hashMap.put(byteKey, new WeakR(byteKey, byteValue, referenceQueue));
        }
    }

    /**
     * 描述一种强key关系的处理，当value值被回收之后，我们可以通过反向引用将key从map中移除的做法
     * 即通过在weakReference中加入其所引用的key值，以获取key信息，再反向移除map信息
     */
    static class WeakR extends WeakReference<byte[]> {
        private Object key;

        public WeakR(Object key, byte[] referent, ReferenceQueue<? super byte[]> q) {
            super(referent, q);
            this.key = key;
        }
    }
}
