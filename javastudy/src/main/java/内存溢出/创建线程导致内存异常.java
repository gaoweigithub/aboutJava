package 内存溢出;

public class 创建线程导致内存异常 {
    private void dontStop(){
        while (true);
    }
    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
            System.out.println(thread.getId()+"started!");
        }
    }

    public static void main(String[] args) {
        创建线程导致内存异常 oom = new 创建线程导致内存异常();
        oom.stackLeakByThread();
    }
}
