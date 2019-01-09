/**
 * @(#)TimeClient.java Created by gw33973 on 2018/4/17   22:21
 * <p>
 * Copyrights (C) 2018保留所有权利
 */

package precases.time.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * (类型功能说明描述)
 * <p>
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2018/4/17 22:21   gw33973     1.0       初始化创建<br>
 * </p>
 *
 * @author gw33973
 * @version 1.0
 * @since JDK1.7
 */
public class TimeClient {
    public static void main(String[] args) throws InterruptedException {
        String host = "127.0.0.1";
        int port = 8080;
        EventLoopGroup workergroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workergroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });
            System.out.println("test1");
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workergroup.shutdownGracefully();
        }
    }
}
