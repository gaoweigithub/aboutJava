/**
 * @(#)TestAtomicDemo.java Created by gw33973 on 2018/5/25   14:18
 * <p>
 * Copyrights (C) 2018保留所有权利
 */

package JUC.原子变量;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2018/5/25 14:18   gw33973     1.0       初始化创建<br>
 * </p>
 *
 * @author gw33973
 * @version 1.0
 * @since JDK1.7
 */
public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicDemo demo = new AtomicDemo();
//        UnAtomicDemo demo = new UnAtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(demo).start();
        }
    }
}
