package com.tabwu.IM.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tabwu.IM.entity.pojo.*;
import com.tabwu.IM.entity.vo.ChatTalkDto;
import com.tabwu.IM.entity.vo.GroupsDto;
import com.tabwu.IM.entity.vo.UserFriendDto;
import com.tabwu.IM.mapper.*;
import com.tabwu.IM.service.NettyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @PROJECT_NAME: IM
 * @USER: tabwu
 * @DATE: 2023/6/9 16:34
 * @DESCRIPTION:
 */
@Service
public class NettyServiceImpl implements NettyService {

    @Resource
    private MsgRecordMapper msgRecordMapper;
    @Resource
    private UserGroupMapper userGroupMapper;
    @Resource
    private GroupMapper groupMapper;
    @Resource
    private TalkListMapper talkListMapper;

    private ExecutorService executors = Executors.newFixedThreadPool(2);

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserFriendMapper userFriendMapper;

    @Override
    public User checkAuth(String userId, String password) {
        return null;
    }

    @Override
    public User queryUserByUserId(String userId) {
        return userMapper.queryUserByUserId(userId);
    }

    @Override
    public List<GroupsDto> queryGroups(String userId) {
        return userMapper.queryGroups(userId);
    }

    @Override
    public List<UserFriendDto> queryFriendsByUserId(String userId) {
        return userMapper.queryFriendsByUserId(userId);
    }

    @Override
    public List<UserGroup> queryGroupsById(String userId) {
        return null;
    }

    @Override
    public List<ChatTalkDto> queryTalkLists(String userId) {
        return userMapper.queryTalkLists(userId);
    }

    @Override
    public void addTalkList(String userId, String talkId, int talkType) {
        TalkList talkList = talkListMapper.selectOne(new QueryWrapper<TalkList>().eq("userId", userId).eq("talkId", talkId).eq("talkType", talkType));
        TalkList talkList1 = talkListMapper.selectOne(new QueryWrapper<TalkList>().eq("userId", talkId).eq("talkId", userId).eq("talkType", talkType));
        if (talkList == null) {
            talkListMapper.insert(new TalkList(userId, talkId, talkType));
        }
        if (talkList1 == null) {
            talkListMapper.insert(new TalkList(talkId, userId, talkType));
        }
    }

    @Override
    public void delTalkList(String userId, String talkId) {

    }

    @Override
    public List<User> queryUserByInfo(String userId, String userKey) {
        return null;
    }

    @Override
    public void addFriendIntoDb(UserFriend userFriend) {
        userFriendMapper.insert(userFriend);
    }

    @Override
    public void delFriendFromDb(String userId, String friendId) {
        userMapper.delFriendFromDb(userId);
        userMapper.delFriendFromDb(friendId);
    }

    @Override
    public void asyncAddUserChatRecord(MsgRecord msgRecord) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                msgRecordMapper.insert(msgRecord);
            }
        });
    }

    @Override
    public List<MsgRecord> queryUserMsgRecords(String talkId, String userId, int talkType) {
        return null;
    }

    @Override
    public void createGroupToDb(String userId, String groupId, String groupName, String groupUrl) {
        userGroupMapper.insert(new UserGroup(userId, groupId));
        groupMapper.insert(new Group(groupId, groupName, groupUrl));
    }

    @Override
    public void userAddGroup(String userId, String groupId) {
        userGroupMapper.insert(new UserGroup(userId, groupId));
    }

    @Override
    public void removeUserFromGroup(String userId, String groupId) {
        userGroupMapper.delete(new UpdateWrapper<UserGroup>().eq("userId", userId).eq("groupId", groupId));
    }

    @Override
    public List<String> queryUserGroupsIds(String userId) {
        return null;
    }

    @Override
    public List<String> queryUserGroupsTalkListIds(String userId) {
        return null;
    }

    @Override
    public List<UserGroup> queryGroupIdByUserId(String sendId) {
        return userGroupMapper.selectList(new QueryWrapper<UserGroup>().eq("userId", sendId));
    }
}
