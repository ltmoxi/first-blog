package com.moses.blog.service;

import com.moses.blog.entity.TypeInfo;
import com.moses.blog.exception.ServiceException;
import com.moses.blog.mapper.ArticleMapper;
import com.moses.blog.mapper.TypeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Moses
 */
@Service("TypeInfoService")
public class TypeInfoService {

    @Autowired
    private TypeInfoMapper typeInfoMapper;

    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 查询所有文章分类
     *
     * @return 返回所有分类
     */
    public List<TypeInfo> list() {
        return typeInfoMapper.getTypeInfoList();
    }

    /**
     * 批量更新/插入文章分类
     *
     * @param id   id
     * @param sort 排序优先级
     * @param name 名称
     */
    public void save(Integer id, Integer sort, String name) {

        //判断这个数据是需要更新还是插入
        if (id == null || id < 1) {
            //插入
            typeInfoMapper.insert(sort, name);
        } else {
            //更新
            typeInfoMapper.update(id, sort, name);
        }

    }

    /**
     * 批量删除文章分类
     *
     * @param id 主键
     */
    public void delete(Integer id) {
        //先统计一下是否有文章使用此类型id,
        int count = articleMapper.countByTypeIdArr(id, 1);
        if (count > 0) {
            //如果有,禁止删除
            throw new ServiceException("存在已经被使用的分类,无法删除!");
        }
        //删除这个idArr中包含的id对应的已经被放入回收站的文章数据
        articleMapper.DeleteByTypeIdArr(id);
        //再删除这个类型数组中包含的类型
        typeInfoMapper.delete(id);
    }


    /**
     * 根据id获取类型信息
     *
     * @param typeId id
     * @return 类型信息
     */
    public TypeInfo findPageInfoById(String typeId) {
        return typeInfoMapper.findPageInfoById(typeId);
    }
}
