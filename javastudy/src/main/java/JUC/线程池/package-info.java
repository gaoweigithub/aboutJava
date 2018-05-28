package JUC.线程池;


/**
 * 第四种获取线程的方法：线程池，一个ExecutorService，它使用可能的几个池线程之一执行每个提交的任务，
 * 通常使用Executors 工厂方法配置。
 * 线程池可以解决两个不同问题：由于减少了每个任务调用的开销，它们通常可以在执行大量异步任务时提供增强的性能，
 * 并且还可以提供绑定和管理资源（包括执行任务集时使用的线程）的方法。
 * 每个ThreadPoolExecutor 还维护着一些基本的统计数据，如完成的任务数。
 * 为了便于跨大量上下文使用，此类提供了很多可调整的参数和扩展钩子(hook)。
 * 但是，强烈建议程序员使用较为方便的Executors 工厂方法,它们均为大多数使用场景预定义了设置。
 * <p>
 * 1,excutors.newcachedthreadpool  无界线程池，可以进行自动线程回收
 * 2,excutors.newfixedthreadpool   固定大小线程池
 * 3,excutors.newsinglethreadpool  单个后台线程
 */