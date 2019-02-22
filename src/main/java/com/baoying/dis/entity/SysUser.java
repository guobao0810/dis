package com.baoying.dis.entity;

import com.baoying.dis.assets.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/15 10:43
 * @Version: 1.0
 */
@Table(name = "sys_user")
@Entity
public class SysUser extends BaseEntity {

    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "status")
    private Integer status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
