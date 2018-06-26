package 内存溢出.虚拟机栈和本地方法栈;

public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak(){
        stackLength ++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        }
        catch (Throwable e){
            System.out.println("stack length:" + oom.stackLength);
            throw  e;
        }
    }
}
