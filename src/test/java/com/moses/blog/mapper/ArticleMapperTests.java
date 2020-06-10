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

public class ArticleMapperTests {
    private AbstractApplicationContext ac;
    private ArticleMapper articleMapper;

    public static void main(String[] args) {

    }


    @Test
    public void insert() {
        Article article = new Article(null,
                "我是标题1",
                "我是正文2",
                "我是简介2",
                "ex/simple2.jpg",
                4,
                new Date(),
                1,
                1,
                "c++");
        articleMapper.insert(article);
    }

    @Test
    public void update() {
        Article article = new Article(1,
                "我是标题2",
                "我是正文2",
                "我是简介2",
                "ex/simple2.jpg",
                4,
                new Date(),
                1,
                1,
                "c++");
        articleMapper.update(article);
    }

    @Test
    public void list() {
        Map<String, Object> param = new HashMap<>();
        param.put("keyWord", "a");



        List<Article> typeInfo = articleMapper.list(param);
        for (Article article : typeInfo) {
            System.err.println(article);
        }
    }

    @Test
    public void findArticleById() {
        Article article = articleMapper.findArticleById(1);
        System.err.println(article);
    }

    @Test
    public void move() {
        Integer[] idArr = new Integer[]{4, 5};
        Integer typeId = 2;
        articleMapper.updateTypeId(idArr,typeId);

    }

    @Test
    public void recycle() {
        Integer id = 4;
        Integer typeId = 0;
        articleMapper.updateStatus(id,typeId);

    }


    @Before
    public void doBefore() {
        ac = new ClassPathXmlApplicationContext("spring-dao.xml");
        articleMapper = ac.getBean("articleMapper", ArticleMapper.class);
    }

    @After
    public void doAfter() {
        ac.close();
    }
}
