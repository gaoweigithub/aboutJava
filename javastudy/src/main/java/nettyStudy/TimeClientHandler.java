/**
 * @(#)TimeClientHandler.java Created by gw33973 on 2018/4/17   22:29
 * <p>
 * Copyrights (C) 2018保留所有权利
 */

package nettyStudy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * (类型功能说明描述)
 * <p>
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2018/4/17 22:29   gw33973     1.0       初始化创建<br>
 * </p>
 *
 * @author gw33973
 * @version 1.0
 * @since JDK1.7
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("server:" + in.toString(CharsetUtil.UTF_8));
        System.out.println("输入应答server的信息:");
        Scanner cin = new Scanner(System.in);
        String content = cin.nextLine();
        final ByteBuf bb = ctx.alloc().buffer();
        bb.writeBytes(content.getBytes());
        final ChannelFuture f = ctx.writeAndFlush(bb);
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("发送成功");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
