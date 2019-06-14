package com.tu.curd.serviceImpl;

import com.tu.curd.dao.ShiroUserDao;
import com.tu.curd.model.ShiroUser;
import com.tu.curd.service.IBaseService;
import com.tu.curd.service.IShiroUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by len on 2019/1/23.
 */
@Service
public class ShiroUserServiceImpl implements IShiroUserService {

    @Autowired
    private ShiroUserDao shiroUserDao;

    public ShiroUser queryModel(int id) {
        return shiroUserDao.selectById(id);
    }

    public ShiroUser queryModel(ShiroUser shiroUser) {
        return shiroUserDao.selectOne(shiroUser);
    }

    public int addModel(ShiroUser shiroUser) {
        return shiroUserDao.insert(shiroUser);
    }

    public int updateModel(ShiroUser shiroUser) {
        return shiroUserDao.update(shiroUser);
    }

    public List<ShiroUser> queryModelList(ShiroUser shiroUser) {
        return shiroUserDao.selectList(shiroUser);
    }

    public List<ShiroUser> queryModelList(Map<String, Object> map) {
        return shiroUserDao.selectList(map);
    }

    public int deleteModel(ShiroUser shiroUser) {
        return shiroUserDao.delete(shiroUser);
    }

    public int deleteModel(Map<String, Object> map) {
        return shiroUserDao.delete(map);
    }


    public ShiroUser queryUserAndRole(Map<String, Object> map) {
        return shiroUserDao.selectUserAndRole(map);
    }
}
