package com.tabwu.IM.utils;

/**
 * @PROJECT_NAME: IM
 * @USER: tabwu
 * @DATE: 2023/6/13 16:05
 * @DESCRIPTION:
 */
public enum ChatConstant {
    LOGIN(1, "登录"),
    SEARCH_FRIEND(2, "搜索好友"),
    ADD_FRIEND(3, "添加好友"),
    DEL_FRIEND(4, "删除好友"),
    SINGLE(5, "单聊"),
    GROUP(6, "群聊"),
    ADD_GROUP(7, "加入群聊"),
    DEL_GROUP(8, "移除群聊"),
    CREATE_GROUP(9, "创建群组"),
    RELINK(10, "断线重连接");


    private int code;
    private String description;

    ChatConstant(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
