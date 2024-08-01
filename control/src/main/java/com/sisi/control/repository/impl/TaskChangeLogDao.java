package com.sisi.control.repository.impl;

import com.sisi.control.model.taskchangelog.TaskChangeLog;
import com.sisi.control.repository.TaskChangeLogRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class TaskChangeLogDao extends AbstractDao<TaskChangeLog, TaskChangeLogRepository>{
    public TaskChangeLogDao(TaskChangeLogRepository taskChangeLogRepository) {
        super(taskChangeLogRepository);
    }





}
