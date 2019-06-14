package com.tu.curd.service;

import com.tu.curd.model.ShiroPermission;

import java.util.List;

/**
 * Created by len on 2019/1/23.
 */
public interface IShiroPermissionService extends IBaseService<ShiroPermission> {

    public List<ShiroPermission> batchQuery(List<Integer> userId);
}
