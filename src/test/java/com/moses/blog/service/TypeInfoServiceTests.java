package com.moses.blog.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TypeInfoServiceTests {

    private AbstractApplicationContext ac;
    private TypeInfoService typeInfoService;



    @Test
    public void save() {

    }


    @Before
    public void doBefore() {
        ac = new ClassPathXmlApplicationContext("spring-dao.xml");
        typeInfoService = ac.getBean("typeInfoService", TypeInfoService.class);
    }

    @After
    public void doAfter() {
        ac.close();
    }
}
