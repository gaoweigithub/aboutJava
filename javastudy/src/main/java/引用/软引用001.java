package 引用;

import java.lang.ref.SoftReference;

/**
 * 软引用（SoftReference），是一种相对强引用弱化一些的引用，可以让对象豁免一些垃圾收集，
 * 只有当 JVM 认为内存不足时，才会去试图回收软引用指向的对象。
 * JVM 会确保在抛出 OutOfMemoryError 之前，清理软引用指向的对象。
 * 软引用通常用来实现内存敏感的缓存，如果还有空闲内存，
 * 就可以暂时保留缓存，当内存不足时清理掉，这样就保证了使用缓存的同时，不会耗尽内存。
 */
public class 软引用001 {
    public static void main(String[] args) {
        SoftReference<Integer> softDDD = new SoftReference<>(1);
        System.out.println(softDDD.get());
    }
}
