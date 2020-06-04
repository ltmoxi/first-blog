package com.moses.blog.mapper;

import com.moses.blog.view.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class ArticleMapperTests {
    private AbstractApplicationContext ac;
    private ArticleMapper articleMapper;


    @Test
    public void insert() {
        Article article = new Article(null,
                "我是标题2",
                "我是正文2",
                "我是简介2",
                "ex/simple2.jpg",
                4,
                new Date(),
                1,
                1);
        articleMapper.insert(article);
    }

    @Test
    public void update() {
        Article article = new Article(1,
                "我是标题1",
                "我是正文2",
                "我是简介2",
                "ex/simple2.jpg",
                4,
                new Date(),
                1,
                1);
        articleMapper.update(article);
    }

    @Test
    public void list() {
        List<Article> typeInfo = articleMapper.list();
        for (Article article : typeInfo) {
            System.err.println(article);
        }
    }

    @Test
    public void findArticleById() {
        Article article = articleMapper.findArticleById(1);
        System.err.println(article);
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
