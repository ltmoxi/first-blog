package com.moses.blog.mapper;

import com.moses.blog.entity.UserInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserInfoMapperTests {
    private AbstractApplicationContext ac;
    private UserInfoMapper userInfoMapper;


    @Test
    public void findUserByUsername() {
        UserInfo userInfo = userInfoMapper.findUserByUsername("123456");
        System.err.println(userInfo);
    }

    @Test
    public void reg() {
        //Integer insert(String username, String password);
        String username = "litian";
        String password = "lidi";
        //row代表着受影响的行数,如果不为1,那么就代表插入错误
        Integer row = userInfoMapper.insert(username, password);
        System.out.println(row);
    }


    @Test
    public void checkUsername() {
        Integer number = userInfoMapper.checkUsername("litian");
        System.out.println(number);
    }

    @Before
    public void doBefore() {
        ac = new ClassPathXmlApplicationContext("spring-dao.xml");
        userInfoMapper = ac.getBean("userInfoMapper", UserInfoMapper.class);
    }

    @After
    public void doAfter() {
        ac.close();
    }
}
