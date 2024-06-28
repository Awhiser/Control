package com.sisi.control;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.context.ControlContext;
import com.sisi.control.model.task.TaskSearchParam;
import com.sisi.control.repository.impl.UserDao;
import com.sisi.control.service.TaskService;
import com.sisi.control.utils.token.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ControlApplicationTests {
    @Autowired
    UserDao userDao;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    TaskService taskService;

    @Test
    void contextLoads() {
       // var c = tokenUtil.getToken("aa","cc");
      //  var a = userDao.findById("");
      //  var resp = Response.success(new UserInfo());
      //  var resp2 = Response.fail(new UserInfo());
      //  ContextHolder.setContext(new ControlContext("1","1","1"));
//        var u = userDao.getUserByUserName("ye");
        TaskSearchParam param = new TaskSearchParam();
        param.setProjectId("S");
        var res = taskService.getTaskList(param);
        System.out.println("111");

    }

}
