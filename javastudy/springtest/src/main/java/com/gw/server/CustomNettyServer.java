package com.gw.server;

import com.gw.custome.PackageScanner;
import com.gw.starter.BaseStarter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.LinkedList;
import java.util.List;

public class CustomNettyServer {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        //启动项扫描
        List<Object> starters = new LinkedList<>();
        new PackageScanner() {
            @Override
            public void dealClass(Class<?> klass) throws IllegalAccessException, InstantiationException {
                if (BaseStarter.class.isAssignableFrom(klass)) {
                    starters.add(klass.newInstance());
                }
            }
        }.packageScanner("com.gw.container");
        //执行starter
        starters.stream().forEach(s -> {
            BaseStarter action = (BaseStarter) s;
            action.process();
        });

        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline()
                                .addLast(new ReadTimeoutHandler(10))
                                .addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(65536))
//                                .addLast(new HttpResponseEncoder())
                                .addLast(new MyHanlder());
                    }
                })
                .bind(8000).sync().channel().closeFuture().sync();
    }
}
