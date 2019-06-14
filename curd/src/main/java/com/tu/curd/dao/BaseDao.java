package com.tu.curd.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by len on 2019/1/22.
 */
public interface BaseDao<M extends Serializable> {

    public int insert(M t);

    public int update(M t);

    public M selectOne(M t);

    public M selectById(int id);

    public List<M> selectList(M t);

    public List<M> selectList(Map<String,Object> map);

    public int delete(M t);

    public int delete(Map<String,Object> map);

}