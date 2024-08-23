package com.sisi.control.service.task;

import com.google.gson.Gson;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.task.TaskDto;
import com.sisi.control.model.taskchangelog.TaskChangeLog;
import com.sisi.control.model.taskchangelog.TaskChangeLogDto;
import com.sisi.control.repository.impl.TaskChangeLogDao;
import com.sisi.control.service.UserService;
import com.sisi.control.service.VersionService;
import com.sisi.control.utils.CommonUtils;
import com.sisi.control.utils.DateUtils;
import com.sisi.control.utils.log.LogHelper;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TaskChangeLogService {

    private TaskChangeLogDao taskChangeLogDao;
    private UserService userService;
    private VersionService versionService;

    @Autowired
    public TaskChangeLogService(TaskChangeLogDao taskChangeLogDao) {
        this.taskChangeLogDao = taskChangeLogDao;
    }

    public void addChangeLogs(List<TaskChangeLog> lists){
        taskChangeLogDao.saveAll(lists);
    }

    public void updateChangeLog(TaskDto originTask, TaskDto updateTask, String operateUser) {
        try {
            List<TaskChangeLog> logs = new ArrayList<>();
            if (!isStringEqual(originTask.getTitle(), updateTask.getTitle())) {
                TaskChangeLog log = new TaskChangeLog();
                log.setName("task.title");
                log.setFromValue(originTask.getTitle());
                log.setToValue(updateTask.getTitle());
                logs.add(log);
            }

            if (!isStringEqual(originTask.getAssignee().getId(), updateTask.getAssignee().getId())) {
                TaskChangeLog log = new TaskChangeLog();
                log.setName("task.assignee");
                var users = userService.getUserByIds(Arrays.asList(originTask.getAssignee().getId(), updateTask.getAssignee().getId()));
                for (var user : users) {
                    if (user.getId().equals(originTask.getAssignee())) {
                        log.setFromValue(user.getDisplayName());
                    } else {
                        log.setToValue(user.getDisplayName());
                    }
                }
                logs.add(log);
            }

            if (!isStringEqual(originTask.getDescription(), updateTask.getDescription())) {
                TaskChangeLog log = new TaskChangeLog();
                log.setName("task.description");
                log.setFromValue(originTask.getDescription());
                log.setToValue(updateTask.getDescription());
                logs.add(log);
            }

//            if (!isStringEqual(originTask.getVersionId(), updateTask.getVersionId())) {
//                TaskChangeLog log = new TaskChangeLog();
//                log.setId(CommonUtils.idGenerate());
//                log.setName("version");
//                var versions = versionService.getByIds(Arrays.asList(originTask.getVersionId(), updateTask.getVersionId()));
//                for (var version : versions) {
//                    if (version.getId().equals(originTask.getVersionId())) {
//                        log.setFromValue(originTask.getVersionId());
//                    } else {
//                        log.setToValue(updateTask.getVersionId());
//                    }
//                }
//                logs.add(log);
//            }

            if (!isStringEqual(originTask.getTags().toString(), updateTask.getTags().toString())) {
                TaskChangeLog log = new TaskChangeLog();
                log.setName("task.tags");
                log.setFromValue(originTask.tags.toString());
                log.setToValue(updateTask.tags.toString());
                logs.add(log);
            }

            if (originTask.getDuedate().compareTo(updateTask.getDuedate()) != 0) {
                TaskChangeLog log = new TaskChangeLog();
                log.setName("task.dueDate");
                log.setFromValue(DateUtils.parseCommonDateStr(originTask.getDuedate()));
                log.setToValue(DateUtils.parseCommonDateStr(updateTask.getDuedate()));
                logs.add(log);
            }

            if (CollectionUtils.isEmpty(logs)) {
                return;
            }


            logs.forEach(i -> {
                i.setId(CommonUtils.idGenerate());
                i.setUserId(operateUser);
                i.setTaskId(originTask.getId());
                i.setOperateTime(new Date());
            });
            //save
            addChangeLogs(logs);
        } catch (Exception e) {
            LogHelper.logError("updateChangeLog Error: originTask: " + new Gson().toJson(originTask) + " || updateTask:  " + new Gson().toJson(updateTask), e);
        }
    }

    //equal
    public boolean isStringEqual(String oldStr, String newStr) {
        var oldVal = StringUtils.hasText(oldStr);
        var newVal = StringUtils.hasText(newStr);
        //全为空
        if (!oldVal && !newVal) {
            return true;
        }
        //有一个为空
        if (!oldVal || !newVal) {
            return false;
        }
        //比较是否相同
        if (oldStr.equals(newStr)) {
            return true;
        }
        return false;
    }


    public List<TaskChangeLogDto> getByTaskId(String taskId){
        return taskChangeLogDao.getByTaskId(taskId);
    }

}
