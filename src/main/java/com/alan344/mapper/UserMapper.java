package com.alan344.mapper;

import com.alan344.model.UserEntity;

/**
 * @author 53479
 * @date 2019/12/23 13:11
 */
public interface UserMapper {
    UserEntity getUser(int userId);
}
