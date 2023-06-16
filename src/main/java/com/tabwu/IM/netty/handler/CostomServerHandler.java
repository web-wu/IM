package com.tabwu.IM.netty.handler;

import com.alibaba.fastjson.JSON;
import com.tabwu.IM.entity.ChatMsg;
import com.tabwu.IM.service.NettyService;
import com.tabwu.IM.utils.ChannelUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * @PROJECT_NAME: IM
 * @USER: tabwu
 * @DATE: 2023/6/13 15:09
 * @DESCRIPTION:
 */
@Slf4j
public class CostomServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final NettyService nettyService;

    private final ChatHandler chatHandler;

    public CostomServerHandler(NettyService nettyService, ChatHandler chatHandler) {
        this.nettyService = nettyService;
        this.chatHandler = chatHandler;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        ChatMsg chatMsg = JSON.parseObject(textWebSocketFrame.text(), ChatMsg.class);
        switch (chatMsg.getType()) {
            case 1:
                chatHandler.loginHandler(ctx, nettyService, chatMsg);
                break;
            case 2:
                chatHandler.searchFriendHandler(ctx, nettyService, chatMsg);
                break;
            case 3:
                chatHandler.addFriendHandler(ctx, nettyService, chatMsg);
                break;
            case 4:
                chatHandler.delFriendHandler(ctx, nettyService, chatMsg);
                break;
            case 5:
                chatHandler.singleHandler(ctx, nettyService, chatMsg);
                break;
            case 6:
                chatHandler.groupHandler(ctx, nettyService, chatMsg);
                break;
            case 7:
                chatHandler.addGroupHandler(ctx, nettyService, chatMsg);
                break;
            case 8:
                chatHandler.removeGroupHandler(ctx, nettyService, chatMsg);
                break;
            case 9:
                chatHandler.createGroupHandler(ctx, nettyService, chatMsg);
                break;
            case 10:
                chatHandler.reLinkHandler(ctx, nettyService, chatMsg);
                break;
            default:
                log.error("没有该 {} 类型的通讯类型", chatMsg.getType());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("用户上线：channelId {}", ctx.channel().id().toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ChannelUtil.removeChannel();
        log.error("netty server run type error");
    }
}
