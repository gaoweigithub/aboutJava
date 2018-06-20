package JUC.Callable接口;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一、创建执行线程的方式三：实现 Callable 接口。 相较于实现 Runnable 接口的方式，方法可以有返回值，并且可以抛出异常。
 * 二、执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。  FutureTask 是  Future 接口的实现类
 */
public class TestCallAble {
    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        //1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(demo);
        new Thread(integerFutureTask).start();

        //2.接收线程运算后的结果
        try {
            //线程在运行的时候，FutureTask 的get方法并没有执行，而是在等待线程运行的结果。FutureTask 可用于 闭锁
            Integer sum = integerFutureTask.get();
            System.out.println(sum);
            System.out.println("---------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
