package com.moses.blog.mapper;

import com.moses.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Moses
 */
public interface ArticleMapper {


    /**
     * 返回限定条件内的所有文章
     *
     * @param param 限定条件
     * @return 符合条件的所有文章
     */
    List<Article> list(Map<String, Object> param);


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

    /**
     * 更新文章数据
     *
     * @param article 要更新的文章数据
     */
    void update(Article article);


    /**
     * 批量更新文章的类型
     *
     * @param idArr  文章id数组
     * @param typeId 类型id
     */
    void updateTypeId(@Param("idArr") Integer[] idArr, @Param("typeId") Integer typeId);

    /**
     * 批量更新文章状态
     *
     * @param idArr  文章id数组
     * @param status 状态码 0为回收状态,1为正常状态
     */
    void updateStatus(@Param("idArr") Integer[] idArr, @Param("status") Integer status);

    /**
     * 批量删除文章
     *
     * @param idArr 文章id数组
     */
    void delete(@Param("idArr") Integer[] idArr);

    /**
     * 根据文章分类,查询文章的数量
     *
     * @param typeIdArr 分类id数组
     * @param status    文章状态
     * @return 该类型id数组下所有的文章数量
     */
    int countByTypeIdArr(@Param("typeIdArr") Integer[] typeIdArr, @Param("status") int status);

    /**
     * 根据文章分离批量删除回收站的文章
     *
     * @param typeIdArr 类型id数组
     */
    void batchDeleteByTypeIdArr(@Param("typeIdArr") Integer[] typeIdArr);
}
