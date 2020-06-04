package com.moses.blog.service;

import com.moses.blog.exception.PasswordNotMatchException;
import com.moses.blog.exception.UserNotFoundException;
import com.moses.blog.mapper.UserInfoMapper;
import com.moses.blog.view.UserInfo;
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
            throw new UserNotFoundException("用户名" + username + "不存在");
        }

        if (!password.equals(userInfo.getPassword())) {
            throw new PasswordNotMatchException("密码错误!");
        }
        return userInfo;
    }

}
