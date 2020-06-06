package com.moses.blog.service;

import com.moses.blog.exception.PasswordNotMatchException;
import com.moses.blog.exception.NotFoundException;
import com.moses.blog.mapper.UserInfoMapper;
import com.moses.blog.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Moses
 */
@Service("userService")
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo login(String username, String password) {
        UserInfo userInfo = userInfoMapper.findUserByUsername(username);
        if (userInfo == null) {
            throw new NotFoundException("用户名" + username + "不存在");
        }

        if (!password.equals(userInfo.getPassword())) {
            throw new PasswordNotMatchException("密码错误!");
        }
        return userInfo;
    }

}
