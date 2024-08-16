package com.sisi.control;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.context.ControlContext;
import com.sisi.control.mq.MQService;
import com.sisi.control.mq.mqconfig.MQConfig;
import com.sisi.control.repository.impl.TaskLinkDao;
import com.sisi.control.repository.impl.UserDao;
import com.sisi.control.service.task.TaskService;
import com.sisi.control.utils.CommonUtils;
import com.sisi.control.utils.token.TokenUtil;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
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

    @Autowired
    MQConfig MQConfig;

    @Autowired
    MQService mqService;

    @Autowired
    TaskLinkDao taskLinkDao;

    @Test
    void contextLoads() {
       // var c = tokenUtil.getToken("aa","cc");
      //  var a = userDao.findById("");
      //  var resp = Response.success(new UserInfo());

        System.out.println(CommonUtils.idGenerate());
      //  var resp2 = Response.fail(new UserInfo());
        ContextHolder.setContext(new ControlContext("1"));
//        var u = userDao.getUserByUserName("ye");
       // TaskSearchParam param = new TaskSearchParam();
        System.out.println(MQConfig.isEnableMQ());
        System.out.println(NanoIdUtils.randomNanoId());
        System.out.println(Thread.currentThread().getName());
        var res=   taskLinkDao.getByTaskId("1");
         res=   taskLinkDao.getByTaskId("1");
//        TaskMessage message = new TaskMessage();
//        message.mqType = MQType.TaskUpdate;
//        message.task = new Task();
//        mqService.publishTaskMsg(message);
//       // param.setProjectId("S");
       // var res = taskService.getTaskList(param);
        System.out.println("111");

        //var res =  new JPACondition<Task>().eq("","").build();

    }

}
