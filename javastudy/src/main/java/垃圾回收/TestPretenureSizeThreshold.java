package 垃圾回收;

/**
 * 基本参数:20m堆内存，老年代10M  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * 超过3M直接分配到老年代: -XX:PretenureSizeThreshold = 3145728
 * 的确已有定论：Paralle Scavenge收集器不认识PretenureSizeThreshold参数
 * https://blog.csdn.net/zjysource/article/details/53748217
 */
public class TestPretenureSizeThreshold {
    private static final int _1MB = 1024*1024;
    public static void main(String[] args) {
        byte[] allocation ;
        allocation = new byte[4*_1MB];//直接分配到老年代中
    }
}
