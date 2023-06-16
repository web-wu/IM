package com.tabwu.IM.entity.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tabwu
 * @since 2023-06-09
 */
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 组id
     */
    private String groupId;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 组头像
     */
    private String groupUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Group() {
    }

    public Group(String groupId, String groupName, String groupUrl) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupUrl = groupUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getGroupUrl() {
        return groupUrl;
    }

    public void setGroupUrl(String groupUrl) {
        this.groupUrl = groupUrl;
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
        return "Group{" +
            "id=" + id +
            ", groupId=" + groupId +
            ", groupName=" + groupName +
            ", groupUrl=" + groupUrl +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
