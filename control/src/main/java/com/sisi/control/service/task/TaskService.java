package com.sisi.control.service.task;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.PageResult;
import com.sisi.control.model.response.Response;
import com.sisi.control.model.task.*;
import com.sisi.control.model.user.UserInfoDto;
import com.sisi.control.mq.MQService;
import com.sisi.control.mq.MQType;
import com.sisi.control.mq.model.TaskMessage;
import com.sisi.control.repository.impl.TaskDao;
import com.sisi.control.service.ProjectService;
import com.sisi.control.service.UserService;
import com.sisi.control.service.VersionService;
import com.sisi.control.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private MQService mqService;

    @Autowired
    public TaskService(TaskDao taskDao, UserService userService, ProjectService projectService, TaskTypeService taskTypeService, VersionService versionService, TaskChangeLogService taskChangeLogService, MQService mqService) {
        this.taskDao = taskDao;
        this.userService = userService;
        this.projectService = projectService;
        this.taskTypeService = taskTypeService;
        this.versionService = versionService;
        this.taskChangeLogService = taskChangeLogService;
        this.mqService = mqService;
    }

    public Response create(Task task) {
        //todo check?
        var tenantId = ContextHolder.getContext().getTenantId();
        task.setId(CommonUtils.idGenerate());
        task.setCreator(ContextHolder.getContext().getToken().userId);
        task.setCreateTime(new Date());
        //todo 实现自定义字段 workFlow create
        taskDao.save(task);
        return Response.success(task.id);
    }

    public TaskDto getTask(String taskId) {
        TaskDto task = taskDao.getById(taskId);
        populateFullData(Arrays.asList(task));
        return task;
    }

    public PageResult<TaskDto> getPage(TaskSearchParam param) {
        var pageRes = taskDao.getTaskPage(param);
        populateFullData(pageRes.getDataList());
        return pageRes;
    }


    public CreateTaskVo getCreateParam() {
        CreateTaskVo vo = new CreateTaskVo();
        var projects = projectService.getProjectByUserId(ContextHolder.getContext().getToken().getUserId());
        vo.setProjectList(projects);
        //获取Type
        var taskTypeList = taskTypeService.getList();
        vo.setTaskTypeList(taskTypeList);
        //获取版本


        //扩展的自定义字段
        return vo;
    }

    public void transition(String taskId, String status) {

    }

    public TaskDto update(Task task) {
        var oldTask = taskDao.getById(task.getId());
        var res = taskDao.save(task);
        mqService.publishTaskMsg(new TaskMessage(MQType.TaskUpdate, res, oldTask));
        return res;
    }


    public void populateFullData(List<TaskDto> taskList){
        HashSet<String> userIds = new HashSet<>();
        taskList.stream().forEach(task -> {
            if (StringUtils.hasText(task.getAssignee().getId())) {
                userIds.add(task.getAssignee().getId());
            }
            if(StringUtils.hasText(task.getCreator().getId())){
                userIds.add(task.getCreator().getId());
            }
        });
        Map<String, UserInfoDto> userMap = new HashMap();
        if(userMap.size() > 0) {
            userMap = userService.getUserByIds(userIds.stream().toList()).stream().collect(Collectors.toMap(i -> i.id, i -> i)) ;
        }

        for(var task : taskList) {
            if(userMap.containsKey(task.getAssignee().getId())){
                task.setAssignee(userMap.get(task.getAssignee().getId()));
            }
            if(userMap.containsKey(task.getCreator().getId())) {
                task.setCreator(userMap.get(task.getCreator().getId()));
            }
        }

    }




}
