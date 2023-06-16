package com.tabwu.IM.entity.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tabwu
 * @since 2023-06-09
 */
public class TalkList implements Serializable {

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
     * 好友id或组id
     */
    private String talkId;

    /**
     * 对话框类型；1-好友，2-群组
     */
    private Integer talkType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public TalkList() {
    }

    public TalkList(String userId, String talkId, Integer talkType) {
        this.userId = userId;
        this.talkId = talkId;
        this.talkType = talkType;
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
    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }
    public Integer getTalkType() {
        return talkType;
    }

    public void setTalkType(Integer talkType) {
        this.talkType = talkType;
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
        return "TalkList{" +
            "id=" + id +
            ", userId=" + userId +
            ", talkId=" + talkId +
            ", talkType=" + talkType +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
