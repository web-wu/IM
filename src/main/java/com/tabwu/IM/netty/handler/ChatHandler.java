package com.tabwu.IM.netty.handler;

import com.alibaba.fastjson.JSON;
import com.tabwu.IM.entity.ChatMsg;
import com.tabwu.IM.entity.pojo.MsgRecord;
import com.tabwu.IM.entity.pojo.User;
import com.tabwu.IM.entity.pojo.UserFriend;
import com.tabwu.IM.entity.pojo.UserGroup;
import com.tabwu.IM.entity.vo.ChatTalkDto;
import com.tabwu.IM.entity.vo.GroupsDto;
import com.tabwu.IM.entity.vo.UserFriendDto;
import com.tabwu.IM.service.NettyService;
import com.tabwu.IM.utils.ChannelUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @PROJECT_NAME: IM
 * @USER: tabwu
 * @DATE: 2023/6/13 16:39
 * @DESCRIPTION:
 */
@Slf4j
public class ChatHandler {

    public void loginHandler(ChannelHandlerContext ctx, NettyService nettyService, ChatMsg chatMsg) {
        User user = nettyService.queryUserByUserId(chatMsg.getSendId());
        ChatMsg result = new ChatMsg();
        if (user.getPassword().equals(chatMsg.getPassword())) {

            ChannelUtil.addChannel(chatMsg.getSendId(), ctx.channel());

            result.setFlag(true);
            result.setContent("登录成功");
            result.setSendId(user.getUserId());
            result.setUserNickName(user.getNickName());
            result.setUserHead(user.getHeadUrl());

            List<ChatTalkDto> chatTalkDtos = nettyService.queryTalkLists(chatMsg.getSendId());
            result.setChatTalkDtos(chatTalkDtos);

            List<GroupsDto> groupsDtos = nettyService.queryGroups(chatMsg.getSendId());
            result.setGroupsDtos(groupsDtos);

            List<UserFriendDto> userFriendDtos = nettyService.queryFriendsByUserId(chatMsg.getSendId());
            result.setUserFriendList(userFriendDtos);

            ctx.writeAndFlush(JSON.toJSONString(result));
        } else {
            result.setFlag(false);
            result.setContent("登录失败");
            ctx.writeAndFlush(JSON.toJSONString(result));
        }
    }

    public void searchFriendHandler(ChannelHandlerContext ctx, NettyService nettyService, ChatMsg chatMsg) {
        User user = nettyService.queryUserByUserId(chatMsg.getSearchId());
        if (user != null) {
            ChatMsg result = new ChatMsg();
            result.setFlag(true);
            result.setContent("搜索成功");
            result.setUser(user);
            ctx.writeAndFlush(JSON.toJSONString(result));
        }
    }

    public void addFriendHandler(ChannelHandlerContext ctx, NettyService nettyService, ChatMsg chatMsg) {
        nettyService.addFriendIntoDb(new UserFriend(chatMsg.getSendId(), chatMsg.getSearchId()));
        nettyService.addFriendIntoDb(new UserFriend( chatMsg.getSearchId(), chatMsg.getSendId()));
        List<UserFriendDto> userFriendDtos = nettyService.queryFriendsByUserId(chatMsg.getSendId());
        ChatMsg result = new ChatMsg();
        result.setFlag(true);
        result.setContent("添加成功");
        result.setUserFriendList(userFriendDtos);
        ctx.writeAndFlush(JSON.toJSONString(result));
    }

    public void delFriendHandler(ChannelHandlerContext ctx, NettyService nettyService, ChatMsg chatMsg) {
        nettyService.delFriendFromDb(chatMsg.getSendId(), chatMsg.getSearchId());
        List<UserFriendDto> userFriendDtos = nettyService.queryFriendsByUserId(chatMsg.getSendId());
        ChatMsg result = new ChatMsg();
        result.setFlag(true);
        result.setContent("删除成功");
        result.setUserFriendList(userFriendDtos);
        ctx.writeAndFlush(JSON.toJSONString(result));
    }

    public void singleHandler(ChannelHandlerContext ctx, NettyService nettyService, ChatMsg chatMsg) {
        Channel channel = ChannelUtil.getChannel(chatMsg.getTalkId());
        nettyService.asyncAddUserChatRecord(new MsgRecord(chatMsg.getSendId(), chatMsg.getTalkId(), 1, chatMsg.getContent(), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())));
        if (channel != null) {
            nettyService.addTalkList(chatMsg.getSendId(), chatMsg.getTalkId(), 1);
            channel.writeAndFlush(JSON.toJSONString(chatMsg));
        }
    }

    public void groupHandler(ChannelHandlerContext ctx, NettyService nettyService, ChatMsg chatMsg) {
        ChannelGroup channelGroup = ChannelUtil.getChannelGroup(chatMsg.getTalkId());
        nettyService.asyncAddUserChatRecord(new MsgRecord(chatMsg.getSendId(), chatMsg.getTalkId(), 2, chatMsg.getContent(), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())));
        if (channelGroup != null) {
            nettyService.addTalkList(chatMsg.getSendId(), chatMsg.getTalkId(), 2);
            channelGroup.writeAndFlush(JSON.toJSONString(chatMsg));
        }
    }

    public void addGroupHandler(ChannelHandlerContext ctx, NettyService nettyService, ChatMsg chatMsg) {
        Channel channel = ChannelUtil.getChannel(chatMsg.getTalkId());
        ChannelUtil.addChannelCroup(chatMsg.getGroupId(), channel);
        nettyService.userAddGroup(chatMsg.getTalkId(), chatMsg.getGroupId());
    }

    public void removeGroupHandler(ChannelHandlerContext ctx, NettyService nettyService, ChatMsg chatMsg) {
        Channel channel = ChannelUtil.getChannel(chatMsg.getTalkId());
        ChannelUtil.removeChannelFromGroup(chatMsg.getTalkId(), channel);
        nettyService.removeUserFromGroup(chatMsg.getTalkId(), chatMsg.getGroupId());
    }

    public void createGroupHandler(ChannelHandlerContext ctx, NettyService nettyService, ChatMsg chatMsg) {
        nettyService.createGroupToDb(chatMsg.getSendId(), chatMsg.getGroupId(), chatMsg.getGroupName(), chatMsg.getGroupUrl());
        List<GroupsDto> groupsDtos = nettyService.queryGroups(chatMsg.getSendId());
        ChatMsg result = new ChatMsg();
        result.setFlag(true);
        result.setContent("创建成功");
        result.setGroupsDtos(groupsDtos);
        ctx.writeAndFlush(JSON.toJSONString(result));
    }

    public void reLinkHandler(ChannelHandlerContext ctx, NettyService nettyService, ChatMsg chatMsg) {
        ChannelUtil.removeChannelByUserId(chatMsg.getSendId());
        ChannelUtil.addChannel(chatMsg.getSendId(), ctx.channel());
        List<UserGroup> userGroups = nettyService.queryGroupIdByUserId(chatMsg.getSendId());
        for (UserGroup userGroup : userGroups) {
            ChannelUtil.addChannelCroup(userGroup.getGroupId(), ctx.channel());
        }
    }
}
