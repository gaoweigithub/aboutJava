package com.gw.server;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;

public class MyHanlder extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        HttpMethod method = msg.method();
        Map<String,Object> parmMap = new HashMap<>();
        System.out.println("请求方式：" + method);
        System.out.println("URL：" + msg.uri());
        if (method == HttpMethod.GET){
            // 是GET请求
            QueryStringDecoder decoder = new QueryStringDecoder(msg.uri());
            decoder.parameters().entrySet().forEach( entry -> {
                // entry.getValue()是一个List, 只取第一个元素
                parmMap.put(entry.getKey(), entry.getValue().get(0));
            });

            System.out.println("请求体："+ JSON.toJSONString(parmMap));

        }else if (HttpMethod.POST == method) {
            //HttpContent content = new DefaultHttpContent(Unpooled.wrappedBuffer(request.content()));
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(msg);
            if(msg.content().isReadable()){
                String json=msg.content().toString(CharsetUtil.UTF_8);
                parmMap.putAll(JSON.parseObject(json));
            }
            decoder.offer(msg);//form
            List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();
            for (InterfaceHttpData parm : parmList) {
                Attribute data = (Attribute) parm;
                parmMap.put(data.getName(), data.getValue());
            }
            System.out.println("请求体："+ JSON.toJSONString(parmMap));

        } else {

        }
        String jobId = JSON.toJSONString(parmMap);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);

        response.headers().set(CONTENT_TYPE, "application/json");

        ByteBuf responseBuf = Unpooled.copiedBuffer(jobId,CharsetUtil.UTF_8);
        response.content().writeBytes(responseBuf);
        responseBuf.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
