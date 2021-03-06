package JUC.线程池.ForkJoinPool;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculate extends RecursiveTask<Long> {
    private static final long serialVersionUID = -259195479995561737L;
    private long start;
    private long end;
    private static final long THURSHOLD = 1000000L;

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THURSHOLD) {
            System.out.println("sum: start:" + start + " end:" + end);
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            System.out.println("processing: start:" + start + " end:" + end);
            long middle = (start + end) / 2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork(); //进行拆分，同时压入线程队列

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
            right.fork();

            return left.join() + right.join();

        }
    }
}
