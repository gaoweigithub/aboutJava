package 垃圾回收;

/**
 * 引用计数---循环引用导致的无法回收
 */
public class RefCountAndRecuise {
    public Object instance = null;
    private static final int _1MB = 1024*1024;
    private byte[] bigsize = new byte[2* _1MB];

    public static void main(String[] args) {
        RefCountAndRecuise obja = new RefCountAndRecuise();
        RefCountAndRecuise objb = new RefCountAndRecuise();

        obja.instance = objb;
        objb.instance = obja;

        obja = null;
        objb = null;
        System.gc();
    }
}
