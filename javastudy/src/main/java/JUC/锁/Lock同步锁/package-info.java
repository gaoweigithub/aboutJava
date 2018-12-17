package JUC.锁.Lock同步锁;
/**
 * 在Java 5.0 之前，协调共享对象的访问时可以使用的机制只有synchronized 和volatile 。
 * Java 5.0 后增加了一些新的机制，但并不是一种替代内置锁的方法，而是当内置锁不适用时，作为一种可选择的高级功能。
 * ReentrantLock 实现了Lock 接口，并提供了与synchronized 相同的互斥性和内存可见性。
 * 但相较于synchronized 提供了更高的处理锁的灵活性。
 */