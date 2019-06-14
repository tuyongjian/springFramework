package com.tu.service.service;

import java.util.List;

/**
 * Created by len on 2019/1/24.
 */
public interface IShiroAccountService {

    public List<String> getPermissionByUserId(int id);
}
