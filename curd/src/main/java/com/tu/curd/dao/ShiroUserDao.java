package com.tu.curd.dao;

import com.tu.curd.model.ShiroUser;

import java.util.Map;

/**
 * Created by len on 2019/1/22.
 */
public interface ShiroUserDao extends BaseDao<ShiroUser> {

    public ShiroUser selectUserAndRole(Map<String,Object> map);


}
