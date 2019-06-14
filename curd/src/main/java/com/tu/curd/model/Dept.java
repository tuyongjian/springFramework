package com.tu.curd.model;

import java.io.Serializable;

/**
 * Created by tuyongjian on 2019/4/15.
 */
public class Dept implements Serializable {

    private String deptNo;

    private String dName;

    private String loc;

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
