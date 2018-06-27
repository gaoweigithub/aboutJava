package 垃圾回收;

public class TestAllocation {
    private static final int _1MB = 1024*1024;

    /**
     * vm参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4,allocation5;
        allocation1 = new byte[2*_1MB];
        allocation2 = new byte[2*_1MB];
        allocation3 = new byte[2*_1MB];
//        allocation5 = new byte[2*_1MB];
        allocation4 = new byte[4*_1MB];
    }
}
