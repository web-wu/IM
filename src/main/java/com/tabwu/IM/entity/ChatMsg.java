package com.tabwu.IM.entity;

import com.tabwu.IM.entity.pojo.User;
import com.tabwu.IM.entity.vo.ChatTalkDto;
import com.tabwu.IM.entity.vo.GroupsDto;
import com.tabwu.IM.entity.vo.UserFriendDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @PROJECT_NAME: IM
 * @USER: tabwu
 * @DATE: 2023/6/13 16:17
 * @DESCRIPTION:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMsg {

    private int type;
    private boolean flag;

    private String sendId;
    private String talkId;

    private String password;
    private String userHead;
    private String userNickName;

    private String searchId;

    private String groupId;
    private String groupName;
    private String groupUrl;

    private String content;

    private User user;

    private List<ChatTalkDto> chatTalkDtos;
    private List<GroupsDto> groupsDtos;
    private List<UserFriendDto> userFriendList = new ArrayList<>();

}
