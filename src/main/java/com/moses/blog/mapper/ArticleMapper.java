package com.moses.blog.mapper;

import com.moses.blog.view.Article;

import java.util.List;

/**
 * @author Moses
 */
public interface ArticleMapper {

    /**
     * 查询所有文章
     *
     * @return 返回文章集合
     */
    List<Article> list();


    /**
     * 插入文章数据
     *
     * @param article 文章数据
     * @return 受影响的行数
     */
    Integer insert(Article article);

    /**
     * 查询指定id的文章
     *
     * @param id id
     * @return 文章
     */
    Article findArticleById(Integer id);

    void update(Article article);
}
