package com.tu.curd.serviceImpl;

import com.tu.curd.dao.ShiroUserRoleDao;
import com.tu.curd.model.ShiroUserRole;
import com.tu.curd.service.IShiroUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by len on 2019/1/23.
 */
@Service
public class ShiroUserRoleServiceImpl implements IShiroUserRoleService {

    @Autowired
    private ShiroUserRoleDao shiroUserRoleDao;


    public ShiroUserRole queryModel(int id) {
        return shiroUserRoleDao.selectById(id);
    }

    public ShiroUserRole queryModel(ShiroUserRole ShiroUserRole) {
        return shiroUserRoleDao.selectOne(ShiroUserRole);
    }

    public int addModel(ShiroUserRole ShiroUserRole) {
        return shiroUserRoleDao.insert(ShiroUserRole);
    }

    public int updateModel(ShiroUserRole ShiroUserRole) {
        return shiroUserRoleDao.update(ShiroUserRole);
    }

    public List<ShiroUserRole> queryModelList(ShiroUserRole shiroUserRole) {
        return shiroUserRoleDao.selectList(shiroUserRole);
    }

    public List<ShiroUserRole> queryModelList(Map<String, Object> map) {
        return shiroUserRoleDao.selectList(map);
    }

    public int deleteModel(ShiroUserRole ShiroUserRole) {
        return shiroUserRoleDao.delete(ShiroUserRole);
    }

    public int deleteModel(Map<String, Object> map) {
        return shiroUserRoleDao.delete(map);
    }
}
