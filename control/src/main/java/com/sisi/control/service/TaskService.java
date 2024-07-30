package com.sisi.control.service;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.PageView;
import com.sisi.control.model.response.Response;
import com.sisi.control.model.task.*;
import com.sisi.control.model.taskchangelog.TaskChangeLog;
import com.sisi.control.repository.impl.TaskDao;
import com.sisi.control.utils.DateUtils;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private TaskDao taskDao;
    private UserService userService;
    private ProjectService projectService;
    private TaskTypeService taskTypeService;
    private VersionService versionService;
    private TaskChangeLogService taskChangeLogService;

    @Autowired
    public TaskService(TaskDao taskDao, UserService userService, ProjectService projectService, TaskTypeService taskTypeService, VersionService versionService, TaskChangeLogService taskChangeLogService) {
        this.taskDao = taskDao;
        this.userService = userService;
        this.projectService = projectService;
        this.taskTypeService = taskTypeService;
        this.versionService = versionService;
        this.taskChangeLogService = taskChangeLogService;
    }

    public Response create(Task task) {
        //todo check?
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
        searchParam.setIds(Arrays.asList(taskId));
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


    public CreateTaskVo getTaskParam(){
        CreateTaskVo vo = new CreateTaskVo();
        var projects =projectService.getProjectByUserId(ContextHolder.getContext().getToken().getUserId());
        vo.setProjectList(projects);
        //获取Type
        var taskTypeList = taskTypeService.getList();
        vo.setTaskTypeList(taskTypeList);
        //获取版本



        //扩展的自定义字段
        return vo;
    }

    public void transition(String taskId,String status){

    }

    public Task update(Task task){
        //update change Log
        var originTask = taskDao.findById(task.getId());
        var res = taskDao.save(task);
        //todo 异步
        updateChangeLog(originTask,task,ContextHolder.getContext().getToken().getUserId());
        return res;
    }

    public void updateChangeLog(Task originTask, Task updateTask, String operateUser) {
        List<TaskChangeLog> logs = new ArrayList<>();
        Date operateTime = new Date();
        String timeTag = DateUtils.parseCommonDateStr(operateTime);

        if (!originTask.getTitle().equals(updateTask.getTitle())) {
            TaskChangeLog log = new TaskChangeLog();
            log.setId(updateTask.getTenantId() + timeTag + NanoIdUtils.randomNanoId());
            log.setName("Title");
            log.setFrom(originTask.getTitle());
            log.setTo(updateTask.getTitle());
            logs.add(log);
        }

        if(!originTask.getAssignee().equals(updateTask.getAssignee())){
            TaskChangeLog log = new TaskChangeLog();
            log.setId(updateTask.getTenantId() + timeTag + NanoIdUtils.randomNanoId());
            log.setName("Assignee");
            var users = userService.getUserByIds(Arrays.asList(originTask.getAssignee(),updateTask.getAssignee()));
            for (var user : users){
                if(user.getId().equals(originTask.getAssignee())){
                    log.setFrom(user.getDisplayName());
                }else{
                    log.setTo(user.getDisplayName());
                }
            }
            logs.add(log);
        }

        if(!originTask.getDescription().equals(updateTask.getDescription())){
            TaskChangeLog log = new TaskChangeLog();
            log.setId(updateTask.getTenantId() + timeTag + NanoIdUtils.randomNanoId());
            log.setName("Description");
            log.setFrom(originTask.getDescription());
            log.setTo(updateTask.getDescription());
            logs.add(log);
        }

        if(!originTask.getVersionId().equals(updateTask.getVersionId())) {
            TaskChangeLog log = new TaskChangeLog();
            log.setId(updateTask.getTenantId() + timeTag + NanoIdUtils.randomNanoId());
            log.setName("version");
            var versions =  versionService.getByIds(Arrays.asList(originTask.getVersionId(),updateTask.getVersionId()));
            for (var version : versions){
                if(version.getId().equals(originTask.getVersionId())){
                    log.setFrom(originTask.getVersionId());
                }else{
                    log.setTo(updateTask.getVersionId());
                }
            }
            logs.add(log);
        }

        if(!originTask.tags.equals(updateTask.tags)) {
            TaskChangeLog log = new TaskChangeLog();
            log.setId(updateTask.getTenantId() + timeTag + NanoIdUtils.randomNanoId());
            log.setName("tags");
            log.setFrom( originTask.tags);
            log.setTo(updateTask.tags);
            logs.add(log);
        }

        if(originTask.getDuedate().compareTo(updateTask.getDuedate()) != 0) {
            TaskChangeLog log = new TaskChangeLog();
            log.setId(updateTask.getTenantId() + timeTag + NanoIdUtils.randomNanoId());
            log.setName("duedate");
            log.setFrom(DateUtils.parseCommonDateStr(originTask.getDuedate()));
            log.setTo(DateUtils.parseCommonDateStr(updateTask.getDuedate()));
            logs.add(log);
        }

        if (CollectionUtils.isEmpty(logs)) {
            return;
        }

        logs.forEach(i -> {
            i.setUserId(operateUser);
            i.setTaskId(originTask.getId());
            i.setOperateTime(operateTime);
        });
        //save
        taskChangeLogService.addChangeLogs(logs);
    }


}
