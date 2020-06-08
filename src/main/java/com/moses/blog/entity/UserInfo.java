package com.moses.blog.entity;

/**
 * 管理员数据
 *
 * @author Moses
 */
public class UserInfo {
    /**
     * 用户的唯一id,(没啥用,我加着好玩的)
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public UserInfo() {

    }

    public UserInfo(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
