package com.gw.server;

import com.alibaba.fastjson.JSON;
import com.gw.container.common.ActionMapUtil;
import com.gw.container.SCContext;
import com.gw.container.ServiceContainer;
import com.gw.container.starter.ContextUtil;
import com.gw.container.model.BaseResponse;
import com.gw.container.model.Header;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;

public class MyHanlder extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        HttpMethod method = msg.method();

        if (HttpMethod.POST != method) {
            writeErrorResponse(ctx, "不接收post以外请求");
            return;
        }

        Map<String, String> parmMap = new HashMap<>();

        String postData = "{}";
        System.out.println("请求方式：" + method);
        System.out.println("URL：" + msg.uri());

        String sourceUrl = StringUtils.split(msg.uri(), "?")[0];
        String[] urlSplits = StringUtils.split(sourceUrl, "/");
        String serviceName = null;
        String actionName = null;
        if (urlSplits.length >= 2) {
            serviceName = urlSplits[urlSplits.length - 2];
            actionName = urlSplits[urlSplits.length - 1];
            //验证
            ActionMapUtil.UrlErrorCode checkResult = ActionMapUtil.check(serviceName, actionName);
            if (checkResult != ActionMapUtil.UrlErrorCode.Ok) {
                writeErrorResponse(ctx, checkResult.getMessage());
                return;
            }

        } else {
            writeErrorResponse(ctx, "服务和动作信息不全");
            return;
        }


        QueryStringDecoder decoder1 = new QueryStringDecoder(msg.uri());
        decoder1.parameters().entrySet().forEach(entry -> {
            // entry.getValue()是一个List, 只取第一个元素
            parmMap.put(entry.getKey(), entry.getValue().get(0));
        });

        if (msg.content().isReadable()) {
            postData = msg.content().toString(CharsetUtil.UTF_8);
        }

        //header
//        Header header = JSON.parseObject(JSON.toJSONString(msg.headers().entries()), Header.class);
        Map<String, String> headerMap = new HashMap<>();
        msg.headers().entries().forEach(en -> {
            headerMap.put(en.getKey().toString(), String.valueOf(en.getValue()));
        });
        Header header = JSON.parseObject(JSON.toJSONString(headerMap), Header.class);

        SCContext scContext = new SCContext();
        scContext.setServiceName(serviceName);
        scContext.setActionName(actionName);
        scContext.setParam(parmMap);
        scContext.setSourceUrl(sourceUrl);
        scContext.setRequestData(postData);
        scContext.setHeader(header);
        BaseResponse response = ServiceContainer.service(scContext, ContextUtil.getContext());

        writeResponse(ctx, response);
    }

    private void writeErrorResponse(ChannelHandlerContext ctx, String message) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        response.setMessage(message);
        writeResponse(ctx, response);
    }

    private void writeResponse(ChannelHandlerContext ctx, Object responseData) {
        if (responseData == null) {
            writeErrorResponse(ctx, "返回无数据");
        } else {
            writeResponse(ctx, JSON.toJSONString(responseData));
        }
    }

    private void writeResponse(ChannelHandlerContext ctx, String responseData) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set(CONTENT_TYPE, "application/json");
        ByteBuf responseBuf = Unpooled.copiedBuffer(responseData, CharsetUtil.UTF_8);
        response.content().writeBytes(responseBuf);
        responseBuf.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
