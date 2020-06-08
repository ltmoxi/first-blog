package com.moses.blog.mapper;

/**
 * @author Moses
 */
public interface TestMapper {

    /**
     * 根据名字,查询id
     *
     * @param username 名字
     * @return id
     */
    Integer getIdByName(String username);

}
