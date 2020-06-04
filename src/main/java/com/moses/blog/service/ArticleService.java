package com.moses.blog.service;

import com.moses.blog.mapper.ArticleMapper;
import com.moses.blog.view.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 列出所有文章
     *
     * @return 所有文章集合
     */
    public List<Article> list() {
        return articleMapper.list();
    }

    /**
     * 插入文章
     *
     * @param article 文章数据
     */
    public void insert(Article article) {
        Integer row = articleMapper.insert(article);
    }

    /**
     * 根据id名查询文章信息
     *
     * @param id 主键
     * @return 文章数据
     */
    public Article findArticleById(Integer id) {
        return articleMapper.findArticleById(id);
    }

    /**
     * 更新文章
     *
     * @param article 文章数据
     */
    public void update(Article article) {
        articleMapper.update(article);
    }
}
