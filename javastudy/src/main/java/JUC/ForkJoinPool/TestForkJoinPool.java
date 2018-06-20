package JUC.ForkJoinPool;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoinPool {
    public static void main(String[] args) {
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 50000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();

        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());//166-1996-10590
    }

    @Test
    public void test1() {
        Instant start = Instant.now();

        long sum = 0L;

        for (long i = 0L; i <= 50000000000L; i++) {
            sum += i;
        }

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());//35-3142-15704
    }

    //java8 新特性
    @Test
    public void test2() {
        Instant start = Instant.now();
        Long sum = LongStream.rangeClosed(0L, 50000000000L)
                .parallel()
                .reduce(0L, Long::sum);

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());//1536-8118
    }
}
