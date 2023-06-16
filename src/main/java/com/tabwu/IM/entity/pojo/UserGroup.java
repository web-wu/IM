package com.tabwu.IM.entity.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tabwu
 * @since 2023-06-09
 */
public class UserGroup implements Serializable {

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
     * 群组id
     */
    private String groupId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public UserGroup() {
    }

    public UserGroup(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
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
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
        return "UserGroup{" +
            "id=" + id +
            ", userId=" + userId +
            ", groupId=" + groupId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
