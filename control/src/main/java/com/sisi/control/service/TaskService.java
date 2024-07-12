package com.sisi.control.service;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.PageView;
import com.sisi.control.model.response.Response;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.task.TaskCreateParam;
import com.sisi.control.model.task.TaskSearchParam;
import com.sisi.control.model.task.TaskVo;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.model.user.UserVo;
import com.sisi.control.repository.impl.TaskDao;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private TaskDao taskDao;
    private UserService userService;

    @Autowired
    public TaskService(TaskDao taskDao, UserService userService) {
        this.taskDao = taskDao;
        this.userService = userService;
    }

    public Response create(TaskCreateParam taskCreateParam) {

        //todo check?

        Task task = new Task(taskCreateParam);
        var tenantId = ContextHolder.getContext().getTenantId();
        task.setId(tenantId + task.projectId + NanoIdUtils.randomNanoId());
        task.setTenantId(tenantId);
        //todo 实现自定义字段 workFlow create
        taskDao.save(task);
        return Response.success(task.id);
    }

    public TaskVo getTask(String taskId) {
//        var task = taskDao.findById(taskId);
//        UserInfo userInfo = null;
//        if (StringUtils.hasText(task.getAssignee())) {
//            userInfo = userService.getUserById(task.getAssignee());
//        }
//        TaskVo taskVo = new TaskVo(task, userInfo);
        TaskSearchParam searchParam = new TaskSearchParam();
        searchParam.setId(taskId);
        var list = getTaskList(searchParam).getDataList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public PageView<TaskVo> getTaskList(TaskSearchParam param) {
        var pageRes = taskDao.getTaskList(param);
        List<Task> taskList = new ArrayList<>();
        List<String> userIds = new ArrayList<>();
        pageRes.stream().forEach(task -> {
            taskList.add(task);
            if (StringUtils.hasText(task.getAssignee())) {
                userIds.add(task.getAssignee());
            }
        });
        var userMap = userService.getUserByIds(userIds).stream().collect(Collectors.toMap(i -> i.id, i -> i));
        var taskVoList = taskList.stream().map(task -> new TaskVo(task,userMap.getOrDefault(task.getAssignee(),null))).toList();
        PageView<TaskVo> taskVoPageView = new PageView(pageRes);
        taskVoPageView.setDataList(taskVoList);
        return taskVoPageView;
    }


}
