/**
 * @(#)Person.java Created by gw33973 on 2018/4/11   23:58
 * <p>
 * Copyrights (C) 2018保留所有权利
 */

package dynamicproxy;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>  
 * 修改日期           修改人员       版本       修改内容<br>  
 * -------------------------------------------------<br>  
 * 2018/4/11 23:58   gw33973     1.0       初始化创建<br>
 * </p> 
 *
 * @author gw33973
 * @version 1.0
 * @since JDK1.7
 */
public interface Person {
    /**
     * 唱歌
     * @param name
     * @return
     */
    String sing(String name);

    /**
     * 跳舞
     * @param name
     * @return
     */
    String dance(String name);
}
