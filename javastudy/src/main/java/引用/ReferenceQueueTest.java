package 引用;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class ReferenceQueueTest {
    public static void main(String[] args) {
        Integer counter = new Integer(123);
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference<Object> p = new PhantomReference<>(counter, referenceQueue);
        counter = null;
        System.gc();

        try {
//            System.out.println(counter);
            Reference<Integer> ref = referenceQueue.remove(1000L);
            if (ref != null) {
                System.out.println(ref.get());
                System.out.println("do something..");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
