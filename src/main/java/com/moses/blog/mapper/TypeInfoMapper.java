package com.moses.blog.mapper;

import com.moses.blog.view.TypeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Moses
 */
public interface TypeInfoMapper {
    /**
     * 查询所有的文章分类
     *
     * @return 返回文章分类集合
     */
    public List<TypeInfo> getTypeInfoList();

    /**
     * 插入一条新的数据
     *
     * @param sort 分类的排序优先级
     * @param name 分类名
     */
    void insert(@Param("sort") Integer sort, @Param("name") String name);

    /**
     * 更新数据
     *
     * @param id   id
     * @param sort 分类的排序优先级
     * @param name 分类名
     */
    void update(@Param("id") Integer id, @Param("sort") Integer sort, @Param("name") String name);

    void delete(@Param("idArr") Integer[] idArr);

    String getNameById(Integer typeId);
}
