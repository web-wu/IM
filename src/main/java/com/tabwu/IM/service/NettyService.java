package com.tabwu.IM.service;

import com.tabwu.IM.entity.pojo.*;
import com.tabwu.IM.entity.vo.ChatTalkDto;
import com.tabwu.IM.entity.vo.GroupsDto;
import com.tabwu.IM.entity.vo.UserFriendDto;

import java.util.List;

/**
 * @PROJECT_NAME: IM
 * @USER: tabwu
 * @DATE: 2023/6/9 16:33
 * @DESCRIPTION:
 */
public interface NettyService {

    //login auth
    User checkAuth(String userId, String password);

    //query user
    User queryUserByUserId(String userId);

    //query group
    List<GroupsDto> queryGroups(String userId);

    //query userFriends list
    List<UserFriendDto> queryFriendsByUserId(String userId);

    //query userGroups list
    List<UserGroup> queryGroupsById(String userId);

    //query user talkList list
    List<ChatTalkDto> queryTalkLists(String userId);

    //add talkList
    void addTalkList(String userId, String talkId, int talkType);

    //del talkList
    void delTalkList(String userId, String talkId);

    //query friend info by xxx
    List<User> queryUserByInfo(String userId, String userKey);

    //add friend
    void addFriendIntoDb(UserFriend userFriend);

    //del friend
    void delFriendFromDb(String userId, String friendId);

    //async add user chat record
    void asyncAddUserChatRecord(MsgRecord msgRecord);

    //query user chat record
    List<MsgRecord> queryUserMsgRecords(String talkId, String userId, int talkType);

    //add group to db
    void createGroupToDb(String userId, String groupId, String groupName, String groupUrl);

    //user add group
    void userAddGroup(String userId, String groupId);

    //remove user from group
    void removeUserFromGroup(String userId, String groupId);

    //query user groupId
    List<String> queryUserGroupsIds(String userId);

    //query user groups talkListId
    List<String> queryUserGroupsTalkListIds(String userId);

    List<UserGroup> queryGroupIdByUserId(String sendId);
}
