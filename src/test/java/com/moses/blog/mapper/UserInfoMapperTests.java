package com.moses.blog.mapper;

import com.moses.blog.view.UserInfo;
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
