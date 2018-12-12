package 引用;

import java.lang.ref.WeakReference;

/**
 * 弱引用（WeakReference）并不能使对象豁免垃圾收集，仅仅是提供一种访问在弱引用状态下对象的途径。
 * 这就可以用来构建一种没有特定约束的关系，比如，维护一种非强制性的映射关系，
 * 如果试图获取时对象还在，就使用它，否则重现实例化。它同样是很多缓存实现的选择。
 */
public class 弱引用001 {
    public static void main(String[] args) {
        WeakReference<Integer> wiii = new WeakReference<>(1);
        System.out.println(wiii.get());
    }
}
