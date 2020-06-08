package com.moses.blog.mapper;

import com.moses.blog.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 插入用户数据
     * @param username 用户名
     * @param password 密码
     * @return 受影响的行数
     */
    Integer insert(@Param("username")String username,@Param("password") String password);

    /**
     * 查询用户名是否存在
     * @param username 用户名
     * @return 与该用户名相同的用户个数
     */
    Integer checkUsername(@Param("username")String username);
}
