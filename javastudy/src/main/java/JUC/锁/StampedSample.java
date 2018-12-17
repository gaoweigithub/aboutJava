package JUC.锁;

import java.util.concurrent.locks.StampedLock;

/**
 * 所以，JDK 在后期引入了 StampedLock，在提供类似读写锁的同时，
 * 还支持优化读模式。优化读基于假设，大多数情况下读操作并不会和写操作冲突，
 * 其逻辑是先试着修改，然后通过 validate 方法确认是否进入了写模式，如果没有进入，
 * 就成功避免了开销；如果进入，则尝试获取读锁。请参考我下面的样例代码。
 */
public class StampedSample {
    private final StampedLock s1 = new StampedLock();

    void mutate() {
        long stamp = s1.writeLock();
        try {
            write();
        } finally {
            s1.unlockWrite(stamp);
        }
    }

    String access() {
        long stamp = s1.tryOptimisticRead();
        String data = read();
        if (!s1.validate(stamp)) {
            stamp = s1.readLock();
            try {
                data = read();
            } finally {
                s1.unlockRead(stamp);
            }
        }
        return data;
    }

    void write() {
        System.out.println("write");
    }

    String read() {
        return "read";
    }
}
