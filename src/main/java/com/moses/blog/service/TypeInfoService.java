package com.moses.blog.service;

import com.moses.blog.entity.TypeInfo;
import com.moses.blog.exception.ServiceException;
import com.moses.blog.mapper.ArticleMapper;
import com.moses.blog.mapper.TypeInfoMapper;
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
     * @param idArr   id数组
     * @param sortArr 排序优先级数组
     * @param nameArr 名称数组
     */
    public void save(String[] idArr, String[] sortArr, String[] nameArr) {
        //idArr  {2  ,9             }
        //nameArr{c--,java ,python }
        //sortArr{1  ,7    ,5       }

        //如果不等与0,那么代表网页上有需要修改的类型数据,需要进行判断,到底是插入,还是修改
        for (int i = 0; i < sortArr.length; i++) {
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
        //先统计一下这个分类id数组中所存的id,
        int count = articleMapper.countByTypeIdArr(idArr, 1);
        if (count > 0) {
            //如果有,禁止删除
            throw new ServiceException("存在已经被使用的分类,无法删除!");
        }
        //批量删除这个idArr中包含的id对应的已经被放入回收站的文章数据
        articleMapper.batchDeleteByTypeIdArr(idArr);
        //再删除这个类型数组中包含的类型
        typeInfoMapper.delete(idArr);
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
