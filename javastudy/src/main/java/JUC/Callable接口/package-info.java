package JUC.Callable接口;
/**
 * Callable 接口。Callable 接口类似于Runnable，两者都是为那些其实例可能被另一个线程执行的类设计的。
 * 但是Runnable 不会返回结果，并且无法抛出经过检查的异常。Callable 需要依赖FutureTask ，FutureTask 也可以用作闭锁。
 * */