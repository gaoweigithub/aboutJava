/**
 * @(#)ldhProxyTest.java Created by gw33973 on 2018/4/12   0:11
 * <p>
 * Copyrights (C) 2018保留所有权利
 */

package dynamicproxyTest;

import dynamicproxy.LiudehuaProxy;
import dynamicproxy.Person;

/**
 * (类型功能说明描述)
 * <p>
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2018/4/12 0:11   gw33973     1.0       初始化创建<br>
 * </p>
 *
 * @author gw33973
 * @version 1.0
 * @since JDK1.7
 */
public class ldhProxyTest {
    public static void main(String[] args) {
        LiudehuaProxy proxy = new LiudehuaProxy();
        Person p = proxy.getProxy();
        String sr = p.sing("冰雨");
        System.out.println(sr);
        String dr = p.dance("江南style");
        System.out.println(dr);
    }
}
