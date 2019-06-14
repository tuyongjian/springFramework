package com.tu.curd.serviceImpl;

import com.tu.common.databaseUtil.HandleDataSource;
import com.tu.curd.dao.DeptDao;
import com.tu.curd.model.Dept;
import com.tu.curd.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by tuyongjian on 2019/4/15.
 */
@Service
public class DeptServiceImpl  implements IDeptService{

    @Autowired
    private DeptDao deptDao;

    public Dept queryModel(int id) {
        return null;
    }

    public Dept queryModel(Dept dept) {
        HandleDataSource.setDbType("dataSourceOracle");
        return deptDao.selectOne(dept);
    }

    public int addModel(Dept dept) {
        return 0;
    }

    public int updateModel(Dept dept) {
        return 0;
    }

    public List<Dept> queryModelList(Dept dept) {
        return null;
    }

    public List<Dept> queryModelList(Map<String, Object> map) {
        return null;
    }

    public int deleteModel(Dept dept) {
        return 0;
    }

    public int deleteModel(Map<String, Object> map) {
        return 0;
    }
}
