package com.tu.curd.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by len on 2019/1/22.
 * shiro  用户表
 */
public class ShiroUser implements Serializable {

    private Integer id;

    private String userName;

    private String password;

    private String isDelete;

    private Timestamp createTime;

    private Timestamp updateTime;

    private List<ShiroUserRole> shiroUserRoles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public List<ShiroUserRole> getShiroUserRoles() {
        return shiroUserRoles;
    }

    public void setShiroUserRoles(List<ShiroUserRole> shiroUserRoles) {
        this.shiroUserRoles = shiroUserRoles;
    }

    @Override
    public String toString() {
        return "ShiroUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", shiroUserRoles=" + shiroUserRoles +
                '}';
    }
}
