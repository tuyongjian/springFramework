package com.tu.service.serviceImpl;

import com.tu.curd.model.ShiroPermission;
import com.tu.curd.model.ShiroUser;
import com.tu.curd.model.ShiroUserRole;
import com.tu.curd.service.IShiroPermissionService;
import com.tu.curd.service.IShiroUserService;
import com.tu.service.service.IShiroAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by len on 2019/1/24.
 */
@Service
public class ShiroAccountServiceImpl implements IShiroAccountService {

    @Autowired
    private IShiroUserService shiroUserService;

    @Autowired
    private IShiroPermissionService shiroPermissionService;

    public List<String> getPermissionByUserId(int id) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        ShiroUser user = this.shiroUserService.queryUserAndRole(map);

        List<ShiroUserRole> shiroUserRoleList = user.getShiroUserRoles();

        List<Integer> list = new ArrayList<Integer>();
        for (ShiroUserRole shiroUserRole: shiroUserRoleList ) {
            list.add(shiroUserRole.getRoleId());
        }

        List<ShiroPermission> shiroPermissionList = this.shiroPermissionService.batchQuery(list);

        List<String> listUrl = new ArrayList<String>();
        for (ShiroPermission shiroPermission:shiroPermissionList) {
            listUrl.add(shiroPermission.getUrl());
        }
        return listUrl;
    }
}
