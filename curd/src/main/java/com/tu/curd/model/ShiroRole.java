package com.tu.curd.model;

import java.io.Serializable;

/**
 * Created by len on 2019/1/22.
 * shiro 角色表
 */
public class ShiroRole implements Serializable{

    private Integer id;

    //角色名
    private String name;

    //角色描述
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ShiroRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
