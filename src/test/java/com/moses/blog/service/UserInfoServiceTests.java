package com.moses.blog.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserInfoServiceTests {

    private AbstractApplicationContext ac;
    private UserInfoService userInfoService;



    @Test
    public void reg() {
        userInfoService.reg("lijia", "1234567");

    }


    @Before
    public void doBefore() {
        ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-mvc.xml");
        userInfoService = ac.getBean("userInfoService", UserInfoService.class);
    }

    @After
    public void doAfter() {
        ac.close();
    }
}
