package JUC.哲学家进餐;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {
    private boolean eating;
    private Philosopher left;
    private Philosopher right;
    private ReentrantLock table;
    private Condition condition;
    private Random random;
    private String name;

    public Philosopher(ReentrantLock table, String name) {
        eating = false;
        this.table = table;
        condition = table.newCondition();
        random = new Random();
        this.name = name;
    }

    public void setLeft(Philosopher left) {
        this.left = left;
    }

    public void setRight(Philosopher right) {
        this.right = right;
    }

    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        table.lock();
        try {
            eating = false;
            left.condition.signal();
            right.condition.signal();
        } finally {
            table.unlock();
        }
        System.out.println(name + "开始思考");
        Thread.sleep(1000);
        System.out.println(name + "思考结束");
    }

    private void eat() throws InterruptedException {
        table.lock();
        try {
            while (left.eating || right.eating) {
                condition.await();
            }
            eating = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            table.unlock();
        }
        System.out.println(name + "开始吃饭");
        Thread.sleep(1000);
        System.out.println(name + "吃饭结束");
    }

    public static void main(String[] args) {
        ReentrantLock table = new ReentrantLock();
        Philosopher p1 = new Philosopher(table, "1");
        Philosopher p2 = new Philosopher(table, "2");
        Philosopher p3 = new Philosopher(table, "3");
        Philosopher p4 = new Philosopher(table, "4");
        Philosopher p5 = new Philosopher(table, "5");

        p1.setLeft(p5);
        p2.setLeft(p1);
        p3.setLeft(p2);
        p4.setLeft(p3);
        p5.setLeft(p4);


        p1.setRight(p2);
        p2.setRight(p3);
        p3.setRight(p4);
        p4.setRight(p5);
        p5.setRight(p1);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}
