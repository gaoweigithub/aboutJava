package Guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestGuaVA<K, V> {
    private Cache<K, V> cache = CacheBuilder.newBuilder().maximumSize(2).expireAfterWrite(10, TimeUnit.MINUTES).build();

    public Object getCache(K keyValue, final String ThreadName) {
        Object value = null;
        try {
            System.out.println("ThreadName getCache==============" + ThreadName);
            //从缓存获取数据
            value = cache.get(keyValue, new Callable<V>() {
                @SuppressWarnings("unchecked")
                public V call() {
                    System.out.println("ThreadName 执行业务数据并返回处理结果的数据（访问数据库等）==============" + ThreadName);
                    return (V) "dataValue";
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return value;
    }


    public static void main(String[] args) {
        final TestGuaVA<String, String> TestGuaVA = new TestGuaVA<String, String>();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("T1======start========");
                Object value = TestGuaVA.getCache("key", "T1");
                System.out.println("T1 value==============" + value);
                System.out.println("T1======end========");

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T2======start========");
                Object value = TestGuaVA.getCache("key", "T2");
                System.out.println("T2 value==============" + value);
                System.out.println("T2======end========");

            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T3======start========");
                Object value = TestGuaVA.getCache("key", "T3");
                System.out.println("T3 value==============" + value);
                System.out.println("T3======end========");

            }
        });

        t1.start();
        t2.start();
        t3.start();


    }


}
