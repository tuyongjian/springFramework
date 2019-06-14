package com.tu.curd.dao;

import com.tu.curd.model.ShiroPermission;

import java.util.List;

/**
 * Created by len on 2019/1/22.
 */
public interface ShiroPermissionDao extends BaseDao<ShiroPermission> {

    public List<ShiroPermission> batchQuery(List<Integer> userId);

}
