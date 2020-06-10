package com.moses.blog.service;

import com.moses.blog.entity.UserInfo;
import com.moses.blog.exception.NotFoundException;
import com.moses.blog.exception.PasswordNotMatchException;
import com.moses.blog.exception.ServiceException;
import com.moses.blog.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Moses
 */
@Service("userInfoService")
public class UserInfoService {
    private final static String USER_NAME_FORMAT = "[a-zA-Z0-9_-]{4,16}";

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

    /**
     * 处理注册用户信息 注册功能,
     *
     * @param username 用户名
     * @param password 密码
     */
    public Integer reg(String username, String password) {
        //用户名不能为空
        if (username == null) {
            throw new ServiceException("用户名不能为空!");
        }
        //用户名只能由字母或者数字组成
        if (!username.matches(USER_NAME_FORMAT)) {
            throw new ServiceException("用户名只能由字母或者数字组成!");
        }

        Integer count = userInfoMapper.checkUsername(username);
        //如果,用户名重复,那么返回false
        if (count > 0) {
            throw new ServiceException("该用户名:" + username + "已被占用!");
        }

        Integer row = userInfoMapper.insert(username, password);
        //如果,受影响的行数不等于1,那么返回false
        if (row != 1) {
            throw new ServiceException("数据库异常!");
        }

        return row;
    }
}
