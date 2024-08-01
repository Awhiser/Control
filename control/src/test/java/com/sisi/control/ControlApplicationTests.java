package com.sisi.control;

import com.sisi.control.model.task.Task;
import com.sisi.control.mq.MQService;
import com.sisi.control.mq.MQType;
import com.sisi.control.mq.model.TaskMessage;
import com.sisi.control.mq.mqconfig.MQConfig;
import com.sisi.control.repository.impl.UserDao;
import com.sisi.control.service.TaskService;
import com.sisi.control.utils.token.TokenUtil;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class ControlApplicationTests {
    @Autowired
    UserDao userDao;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    TaskService taskService;

    @Autowired
    MQConfig MQConfig;

    @Autowired
    MQService mqService;

    @Test
    void contextLoads() {
       // var c = tokenUtil.getToken("aa","cc");
      //  var a = userDao.findById("");
      //  var resp = Response.success(new UserInfo());
      //  var resp2 = Response.fail(new UserInfo());
      //  ContextHolder.setContext(new ControlContext("1","1","1"));
//        var u = userDao.getUserByUserName("ye");
       // TaskSearchParam param = new TaskSearchParam();
        System.out.println(MQConfig.isEnableMQ());
        System.out.println(NanoIdUtils.randomNanoId());
        System.out.println(Thread.currentThread().getName());
        TaskMessage message = new TaskMessage();
        message.mqType = MQType.TaskUpdate;
        message.task = new Task();
        mqService.publishTaskMsg(message);
       // param.setProjectId("S");
       // var res = taskService.getTaskList(param);
        System.out.println("111");

    }

}
