package com.moses.blog.service;

import com.moses.blog.mapper.ArticleMapper;
import com.moses.blog.view.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Moses
 */
@Service("ArticleService")
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TypeInfoService typeInfoService;

    public List<Article> list() {
        return articleMapper.list();
    }

    public void insert(Article article,Integer typeId) {
        String typeName = typeInfoService.getNameById(typeId);
        article.setTypeName(typeName);
        Integer row = articleMapper.insert(article);
    }

    /**
     * 根据id名查询文章信息
     *
     * @param id 主键
     * @return
     */
    public Article findArticleById(Integer id) {
        return articleMapper.findArticleById(id);
    }

    public void update(Article article,Integer typeId) {
        String typeName = typeInfoService.getNameById(typeId);
        article.setTypeName(typeName);
        articleMapper.update(article);
    }
}
