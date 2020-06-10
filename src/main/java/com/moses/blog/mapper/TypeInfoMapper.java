package com.moses.blog.mapper;

import com.moses.blog.entity.TypeInfo;
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
    List<TypeInfo> getTypeInfoList();

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

    /**
     * 删除指定id的类型
     *
     * @param id id数组
     */
    void delete(@Param("id") Integer id);

    /**
     * 通过id获取类型的名称
     *
     * @param typeId 类型id
     * @return 类型名称
     */
    String getNameById(Integer typeId);

    /**
     * 通过id获取类型的信息
     * @param typeId 类型id
     * @return 类型信息
     */
    TypeInfo findPageInfoById(String typeId);
}
