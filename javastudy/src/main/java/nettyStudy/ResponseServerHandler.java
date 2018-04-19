/**
 * @(#)ResponseServerHandler.java Created by gw33973 on 2018/4/17   21:36
 * <p>
 * Copyrights (C) 2018保留所有权利
 */

package nettyStudy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>  
 * 修改日期           修改人员       版本       修改内容<br>  
 * -------------------------------------------------<br>  
 * 2018/4/17 21:36   gw33973     1.0       初始化创建<br>
 * </p> 
 *
 * @author gw33973
 * @version 1.0
 * @since JDK1.7
 */
public class ResponseServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println(in.toString(CharsetUtil.UTF_8));
        ctx.write(msg);
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
