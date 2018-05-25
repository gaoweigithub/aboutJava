/**
 * @(#)UnAtomicDemo.java Created by gw33973 on 2018/5/25   14:20
 * <p>
 * Copyrights (C) 2018保留所有权利
 */

package JUC.原子变量;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2018/5/25 14:20   gw33973     1.0       初始化创建<br>
 * </p>
 *
 * @author gw33973
 * @version 1.0
 * @since JDK1.7
 */
public class UnAtomicDemo implements Runnable {
    public int getSerialNumber() {
        return serialNumber++;
    }

//    private volatile Integer serialNumber = new Integer(0);
    private Integer serialNumber = new Integer(0);

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getSerialNumber());
    }
}
