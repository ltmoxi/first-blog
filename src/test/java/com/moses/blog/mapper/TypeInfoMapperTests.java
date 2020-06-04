package com.moses.blog.mapper;

import com.moses.blog.view.TypeInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TypeInfoMapperTests {
    private AbstractApplicationContext ac;
    private TypeInfoMapper typeInfoMapper;


    @Test
    public void insert() {
        TypeInfo typeInfo = new TypeInfo(null, "python", 3);
        typeInfoMapper.insert(3, "python");
    }

    @Test
    public void update() {
        typeInfoMapper.update(2,1,"C++");
    }

    @Test
    public void getName() {
        System.out.println(typeInfoMapper.getNameById(2));
    }


    @Before
    public void doBefore() {
        ac = new ClassPathXmlApplicationContext("spring-dao.xml");
        typeInfoMapper = ac.getBean("typeInfoMapper", TypeInfoMapper.class);
    }

    @After
    public void doAfter() {
        ac.close();
    }
}
