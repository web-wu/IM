package com.tabwu.IM.netty;

import com.tabwu.IM.service.NettyService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @PROJECT_NAME: IM
 * @USER: tabwu
 * @DATE: 2023/6/9 16:29
 * @DESCRIPTION:
 */
@Component
@Slf4j
public class NettyServer {

    @Autowired
    private NettyService nettyService;

    public void serverStart(String host, int port) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EventLoopGroup parentGroup = new NioEventLoopGroup(2);
                    EventLoopGroup childGroup = new NioEventLoopGroup();
                    ServerBootstrap serverBootstrap = new ServerBootstrap();
                    serverBootstrap
                            .group(parentGroup, childGroup)
                            .channel(NioServerSocketChannel.class)
                            .childOption(ChannelOption.SO_KEEPALIVE, true)
                            .childHandler(new ChanneInitialize(nettyService));
                    ChannelFuture channelFuture = serverBootstrap.bind(host, port).sync();
                    log.info("netty server start run in port {}", port);
                    channelFuture.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    log.error("netty server run error, {}", e);
                }
            }
        }).start();
    }
}
