package JUC.生产者消费者;

public class Clerk {
    private int product = 0;

    //进货
    public synchronized void get(){//循环次数：0
        while(product >= 1){//为了避免虚假唤醒问题，应该总是使用在循环中
            System.out.println("产品已满！");

            try {
                this.wait();
            } catch (InterruptedException e) {
            }

        }

        System.out.println(Thread.currentThread().getName() + " : " + ++product);
        this.notifyAll();
    }

    //卖货
    public synchronized void sale(){//product = 0; 循环次数：0
        while(product <= 0){
            System.out.println("缺货！");

            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }

        System.out.println(Thread.currentThread().getName() + " : " + --product);
        this.notifyAll();
    }
}
