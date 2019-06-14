package com.tu.curd.serviceImpl;

import com.tu.curd.dao.UserDao;
import com.tu.curd.model.User;
import com.tu.curd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tuyongjian on 2019/1/6.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    public User queryUser(int id) {
        return userDao.selectUser(id);
    }

    public int addUser(User user) {
        int result = userDao.insert(user);
        return result;
    }

    public int updateUser(User user) {
        int result = userDao.update(user);
        return result;
    }

    public int queryCount() {
        return userDao.selectCount();
    }

    public int procedure() {
        return userDao.procedure();
    }
}
