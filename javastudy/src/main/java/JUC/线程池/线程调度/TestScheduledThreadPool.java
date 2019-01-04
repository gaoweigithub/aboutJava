package JUC.线程池.线程调度;

import java.util.Date;
import java.util.concurrent.*;

public class TestScheduledThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        /**
         * 固定时间间隔执行线程
         */
        for (;;) {
            Future<Long> result = pool.schedule(() -> {
                Long num = new Date().getTime();
                return num;
            }, 100, TimeUnit.MILLISECONDS);

            System.out.println(result.get() / 1000);
        }


        /**
         * 固定速率执行，如下是延迟300ms，每隔1ms执行一次
         */
//        for (int i = 0; i < 5; i++) {
//            pool.scheduleAtFixedRate(new Runnable() {
//                @Override
//                public void run() {
//                    long time = new Date().getTime();
//                    System.out.println(time / 1000 + "  " + time % 1000);
//                }
//            }, 300, 1, TimeUnit.MILLISECONDS);
//        }


        /**
         * 固定延迟执行线程
         */
//        for (int i = 0; i < 5; i++) {
//            pool.scheduleWithFixedDelay(new Runnable() {
//                @Override
//                public void run() {
//                    long time = new Date().getTime();
//                    System.out.println(time / 1000 + "  " + time % 1000);
//                }
//            }, 1, 1, TimeUnit.SECONDS);
//        }
    }
}
