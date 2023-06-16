package com.tabwu.IM.utils;


import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @PROJECT_NAME: IM
 * @USER: tabwu
 * @DATE: 2023/6/13 15:24
 * @DESCRIPTION:
 */
public class ChannelUtil {

    private static ConcurrentHashMap<String, Channel> userChannel = new ConcurrentHashMap();
    private static ConcurrentHashMap<String, String> userIdMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

    //添加用户id与channel映射
    public static void addChannel(String userId, Channel channel) {
        userChannel.put(userId, channel);
        userIdMap.put(channel.id().toString(), userId);
    }

    //添加用户id与channel映射
    public static void removeChannelByUserId(String userId) {
        Channel channel = userChannel.remove(userId);
        userIdMap.remove(channel.id().toString());
    }

    //根据用户id获取channel
    public static Channel getChannel(String userId) {
        return userChannel.get(userId);
    }

    //根据channel id 获取userId
    public static String getUserIdByChannelId(String channelId) {
        return userIdMap.get(channelId);
    }

    //清楚全部映射
    public static void removeChannel() {
        userChannel.clear();
        userIdMap.clear();
        channelGroupMap.clear();
    }

    //向群组里面添加用户
    public static void addChannelCroup(String groupId, Channel userChannel) {
        ChannelGroup channelGroup = channelGroupMap.get(groupId);
        if (channelGroup == null) {
            channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            channelGroupMap.put(groupId, channelGroup);
        }
        channelGroup.add(userChannel);
    }

    //获取群组
    public static ChannelGroup getChannelGroup(String groupId) {
        return channelGroupMap.get(groupId);
    }

    //将用户从群组里面移除
    public static void removeChannelFromGroup(String groupId, Channel userChannel) {
        ChannelGroup channelGroup = channelGroupMap.get(groupId);
        if (channelGroup != null) {
            channelGroup.remove(userChannel);
        }
    }

}
