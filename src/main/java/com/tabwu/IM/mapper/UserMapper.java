package com.tabwu.IM.mapper;

import com.tabwu.IM.entity.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tabwu.IM.entity.vo.ChatTalkDto;
import com.tabwu.IM.entity.vo.GroupsDto;
import com.tabwu.IM.entity.vo.UserFriendDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author tabwu
 * @since 2023-06-09
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT tk.talkId, tk.talkType, u.nickName, u.headUrl AS 'talkHead' FROM talklist tk JOIN `user` ON tk.talkId = u.userId WHERE tk.userId = #{userId};")
    List<ChatTalkDto> queryTalkLists(String userId);

    @Select("SELECT g.groupId, g.groupName, g.groupUrl AS 'groupHead' FROM `usergroup` ug JOIN `group` ON ug.groupId = g.groupId WHERE ug.userId = #{userId};")
    List<GroupsDto> queryGroups(String userId);

    @Select("SELECT uf.friendId, u.nickName, u.headUrl AS 'friendHead' FROM userfriend JOIN `user` ON uf.friendId = u.userId WHERE uf.userId = #{userId};")
    List<UserFriendDto> queryFriendsByUserId(String userId);

    @Select("SELECT u.userId, u.nickName, u.headUrl FROM `user` u WHERE u.userId = #{userId}")
    User queryUserByUserId(String userId);

    @Delete("DELETE FROM userfriend WHERE userId = #{userId}")
    void delFriendFromDb(String userId);
}
