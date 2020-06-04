package com.moses.blog.service;

import com.moses.blog.mapper.TypeInfoMapper;
import com.moses.blog.view.TypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Moses
 */
@Service("TypeInfoService")
public class TypeInfoService {

    @Autowired
    private TypeInfoMapper typeInfoMapper;


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
     * @param idArr   id数组
     * @param sortArr 排序优先级数组
     * @param nameArr 名称数组
     */
    public void save(String[] idArr, String[] sortArr, String[] nameArr) {
        //遍历第一个数组
        for (int i = 0; i < idArr.length; i++) {
            //判断这个数据是需要更新还是插入
            if (StringUtils.isEmpty(idArr[i])) {
                //插入
                typeInfoMapper.insert(Integer.parseInt(sortArr[i]), nameArr[i]);
            } else {
                //更新
                typeInfoMapper.update(Integer.parseInt(idArr[i]), Integer.parseInt(sortArr[i]), nameArr[i]);
            }
        }
    }

    /**
     * 批量删除文章分类
     *
     * @param idArr 主键数组
     */
    public void delete(Integer[] idArr) {
        typeInfoMapper.delete(idArr);
    }

    public String getNameById(Integer typeId) {
        return typeInfoMapper.getNameById(typeId);
    }
}
