package com.tu.curd.service;

import com.tu.curd.model.User;

import java.util.List;

/**
 * Created by tuyongjian on 2019/1/6.
 */
public interface IUserService {

    User queryUser(int id);

    int addUser(User user);

    int updateUser(User user);

    int queryCount();

    int procedure();

    List<User> queryUserByPage();
}
