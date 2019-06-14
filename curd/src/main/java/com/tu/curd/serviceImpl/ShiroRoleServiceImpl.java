package com.tu.curd.serviceImpl;

import com.tu.curd.dao.ShiroRoleDao;
import com.tu.curd.dao.ShiroUserDao;
import com.tu.curd.model.ShiroRole;
import com.tu.curd.model.ShiroUser;
import com.tu.curd.service.IShiroRoleService;
import com.tu.curd.service.IShiroUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by len on 2019/1/23.
 */
@Service
public class ShiroRoleServiceImpl implements IShiroRoleService {

    @Autowired
    private ShiroRoleDao shiroRoleDao;


    public ShiroRole queryModel(int id) {
        return shiroRoleDao.selectById(id);
    }

    public ShiroRole queryModel(ShiroRole shiroRole) {
        return shiroRoleDao.selectOne(shiroRole);
    }

    public int addModel(ShiroRole shiroRole) {
        return shiroRoleDao.insert(shiroRole);
    }

    public int updateModel(ShiroRole shiroRole) {
        return shiroRoleDao.update(shiroRole);
    }

    public List<ShiroRole> queryModelList(ShiroRole shiroRole) {
        return shiroRoleDao.selectList(shiroRole);
    }

    public List<ShiroRole> queryModelList(Map<String, Object> map) {
        return shiroRoleDao.selectList(map);
    }

    public int deleteModel(ShiroRole shiroRole) {
        return shiroRoleDao.delete(shiroRole);
    }

    public int deleteModel(Map<String, Object> map) {
        return shiroRoleDao.delete(map);
    }
}
