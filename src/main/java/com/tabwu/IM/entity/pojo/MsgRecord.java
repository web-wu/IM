package com.tabwu.IM.entity.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tabwu
 * @since 2023-06-09
 */
public class MsgRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 好友id
     */
    private String friendId;

    /**
     * 对话类型；1-好友，2-群组
     */
    private Integer talkType;

    /**
     * 消息
     */
    private String msg;

    /**
     * 消息时间
     */
    private String msgTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public MsgRecord() {
    }

    public MsgRecord(String userId, String friendId, Integer talkType, String msg, String msgTime) {
        this.userId = userId;
        this.friendId = friendId;
        this.talkType = talkType;
        this.msg = msg;
        this.msgTime = msgTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
    public Integer getTalkType() {
        return talkType;
    }

    public void setTalkType(Integer talkType) {
        this.talkType = talkType;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MsgRecord{" +
            "id=" + id +
            ", userId=" + userId +
            ", friendId=" + friendId +
            ", talkType=" + talkType +
            ", msg=" + msg +
            ", msgTime=" + msgTime +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
