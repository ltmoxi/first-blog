package com.moses.blog.mapper;

import com.moses.blog.entity.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMapperTests {
    //不要管
    private AbstractApplicationContext ac;
    //对应着上面的TestMapper接口
    private TestMapper testMapper;


    @Test
    public void test() {
        Integer id = testMapper.getIdByName("123456");
        System.out.println(id);
    }


    @Before
    public void doBefore() {
        ac = new ClassPathXmlApplicationContext("spring-dao.xml");
        testMapper = ac.getBean("testMapper", TestMapper.class);
    }

    @After
    public void doAfter() {
        ac.close();
    }
}
