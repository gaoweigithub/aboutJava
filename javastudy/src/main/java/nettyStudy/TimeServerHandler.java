/**
 * @(#)DisCardServerHandler.java Created by gw33973 on 2018/4/17   17:22
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
import io.netty.util.ReferenceCountUtil;

import java.util.Scanner;

/**
 * (类型功能说明描述)
 * <p>
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2018/4/17 17:22   gw33973     1.0       初始化创建<br>
 * </p>
 *
 * @author gw33973
 * @version 1.0
 * @since JDK1.7
 */
public class TimeServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        Scanner cin = new Scanner(System.in);
        System.out.println("请输入发送的消息:");
        String name = "Hello " + cin.nextLine();
//        String name = "hello world";
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeBytes(name.getBytes());
        final ChannelFuture f = ctx.writeAndFlush(time);
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                assert f == future;
                ctx.close();
            }
        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf in = (ByteBuf) msg;
            System.out.println("client:" + in.toString(CharsetUtil.UTF_8));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
