package com.tabwu.IM.netty;

import com.tabwu.IM.netty.handler.ChatHandler;
import com.tabwu.IM.netty.handler.CostomServerHandler;
import com.tabwu.IM.service.NettyService;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @PROJECT_NAME: IM
 * @USER: tabwu
 * @DATE: 2023/6/13 14:50
 * @DESCRIPTION:
 */
public class ChanneInitialize extends ChannelInitializer<SocketChannel> {

    private final NettyService nettyService;

    public ChanneInitialize(NettyService nettyService) {
        this.nettyService = nettyService;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(5120));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new CostomServerHandler(nettyService, new ChatHandler()));
    }
}
