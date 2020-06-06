package com.moses.blog.entity;

/**
 * 文章分类
 * @author Moses
 */
public class TypeInfo {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 文章分类的名字
     */
    private String name;
    /**
     * 文章分类的顺序(优先级)
     */
    private Integer sort;

    public TypeInfo() {

    }

    public TypeInfo(Integer id, String name, Integer sort) {
        this.id = id;
        this.name = name;
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "TypeInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
