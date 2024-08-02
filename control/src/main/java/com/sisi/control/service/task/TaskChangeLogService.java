package com.sisi.control.service.task;

import com.sisi.control.model.taskchangelog.TaskChangeLog;
import com.sisi.control.repository.impl.TaskChangeLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskChangeLogService {

    private TaskChangeLogDao taskChangeLogDao;

    @Autowired
    public TaskChangeLogService(TaskChangeLogDao taskChangeLogDao) {
        this.taskChangeLogDao = taskChangeLogDao;
    }

    public void addChangeLogs(List<TaskChangeLog> lists){
        taskChangeLogDao.saveAll(lists);
    }



}
