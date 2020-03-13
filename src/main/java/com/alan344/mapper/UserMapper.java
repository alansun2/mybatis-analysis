package com.alan344.mapper;

import com.alan344.model.UserEntity;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 53479
 * @date 2019/12/23 13:11
 */
public interface UserMapper {
    List<UserEntity> getUser(int userId);

    Cursor<UserEntity> getUsers(RowBounds rb);

    @MapKey("id")
    Map<String, UserEntity> getKeyMap(Set<Integer> userId);
}
