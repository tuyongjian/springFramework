package com.tu.service.action;

import com.tu.common.dto.Result;
import com.tu.curd.model.Dept;
import com.tu.curd.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tuyongjian on 2019/4/15.
 */
@Controller
@RequestMapping(value = "oracle")
public class OracleAction {

    @Autowired
    private IDeptService deptService;

    @ResponseBody
    @RequestMapping(value = "test",method = RequestMethod.POST)
    private Result test(){
        Result result = new Result();
        Dept dept = new Dept();
        dept.setDeptNo("10");
        Dept  dept1 = deptService.queryModel(dept);
         result.setData(dept1);
        return result;
    }

}
