package com.moses.blog.service;

import com.moses.blog.mapper.ArticleMapper;
import com.moses.blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public List<Article> list(Map<String, Object> param) {
        return articleMapper.list(param);
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

    /**
     * 批量更改文章的类型
     * @param idArr 文章id数组
     * @param typeId 类型id
     */
    public void updateTypeId(Integer[] idArr, Integer typeId) {
        articleMapper.updateTypeId(idArr, typeId);
    }

    /**
     * 更新文章状态(放入回收站或者还原)
     * @param id id
     * @param status 状态
     */
    public void updateStatus(Integer id, Integer status) {
        articleMapper.updateStatus(id, status);
    }

    /**
     * 删除文章
     * @param id 文章id
     */
    public void delete(Integer id) {
        articleMapper.delete(id);
    }
}
