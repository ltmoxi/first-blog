package com.moses.blog.mapper;

import com.moses.blog.view.UserInfo;

/**
 * @author Moses
 */
public interface UserInfoMapper {

    /**
     * 通过用户名找到用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UserInfo findUserByUsername(String username);
}
