package com.tu.curd.model;

import java.io.Serializable;

/**
 * Created by len on 2019/1/22.
 *
 * shiro 权限表
 *
 */
public class ShiroPermission implements Serializable{

    private Integer id;

    private String token;

    //资源的url
    private String url;

    //权限说明
    private String description;

    //角色编号
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "ShiroPermission{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
