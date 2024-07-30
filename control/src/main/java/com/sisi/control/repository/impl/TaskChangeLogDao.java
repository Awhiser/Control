package com.sisi.control.repository.impl;

import com.sisi.control.model.taskchangelog.TaskChangeLog;
import com.sisi.control.repository.TaskChangeLogRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TaskChangeLogDao extends AbstractDao<TaskChangeLog, TaskChangeLogRepository>{
    public TaskChangeLogDao(TaskChangeLogRepository taskChangeLogRepository) {
        super(taskChangeLogRepository);
    }





}
