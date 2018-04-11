/**
 * @(#)Liudehua.java Created by gw33973 on 2018/4/12   0:01
 * <p>
 * Copyrights (C) 2018保留所有权利
 */

package dynamicproxy;

/**
 * (类型功能说明描述)
 * <p>
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2018/4/12 0:01   gw33973     1.0       初始化创建<br>
 * </p>
 *
 * @author gw33973
 * @version 1.0
 * @since JDK1.7
 */
public class Liudehua implements Person {
    public String sing(String name) {
        System.out.println("刘德华唱歌:" + name);
        return "唱完了  谢谢大家";
    }

    public String dance(String name) {
        System.out.println("刘德华跳舞" + name);
        return "跳完了 谢谢大家";
    }
}
