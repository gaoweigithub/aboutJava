/**
 * @(#)treemaptest.java Created by gw33973 on 2018/5/23   10:02
 * <p>
 * Copyrights (C) 2018保留所有权利
 */

package treemap;

import java.util.TreeMap;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2018/5/23 10:02   gw33973     1.0       初始化创建<br>
 * </p>
 *
 * @author gw33973
 * @version 1.0
 * @since JDK1.7
 */
public class treemaptest {
    public static void main(String[] args) {
        TreeMap<Integer, String> tp = new TreeMap();
        tp.put(1, "1");
        tp.put(4, "4");
        tp.put(6, "6");
        tp.put(5, "5");
        tp.put(3, "3");
        tp.put(2, "2");
        for (Integer key : tp.keySet()) {
            System.out.println(String.format("key:%s  value:%s", key, tp.get(key)));
        }
    }
}
