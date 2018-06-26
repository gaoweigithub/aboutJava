package 内存溢出.方法区和运行时常量池溢出;

import java.util.ArrayList;
import java.util.List;

/**
 * jdk1.6会爆oom    1.7之后不会   因为方法区已经放到堆里了
 */
public class runtimeConstantPoolOmm {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
