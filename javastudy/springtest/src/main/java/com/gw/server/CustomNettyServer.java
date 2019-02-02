package com.gw.server;

import com.gw.container.starter.StarterOrderAnnotation;
import com.gw.scanner.PackageScanner;
import com.gw.container.starter.BaseStarter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.TreeMap;

public class CustomNettyServer {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        //启动项扫描
        TreeMap<Integer, BaseStarter> baseStarterTreeMap = new TreeMap<>();
        new PackageScanner() {
            @Override
            public void dealClass(Class<?> klass) throws IllegalAccessException, InstantiationException {
                if (BaseStarter.class.isAssignableFrom(klass)
                        && klass != BaseStarter.class
                        && !klass.isAnnotationPresent(Deprecated.class)) {
                    StarterOrderAnnotation orderAnnotation = klass.getAnnotation(StarterOrderAnnotation.class);
                    Integer order = Integer.MAX_VALUE;
                    if (orderAnnotation != null) {
                        order = orderAnnotation.order();
                    }
                    baseStarterTreeMap.put(order, (BaseStarter) klass.newInstance());
                }
            }
        }.packageScanner("com.gw.container");
        //执行starter
        baseStarterTreeMap.entrySet().forEach(en -> {
            en.getValue().process();
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
