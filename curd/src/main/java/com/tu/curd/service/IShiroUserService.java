package com.tu.curd.service;

import com.tu.curd.model.ShiroUser;

import java.util.Map;

/**
 * Created by len on 2019/1/23.
 */
public interface IShiroUserService extends IBaseService<ShiroUser> {

    public ShiroUser queryUserAndRole(Map<String,Object> map);
}
