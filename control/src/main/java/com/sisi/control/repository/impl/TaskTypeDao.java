package com.sisi.control.repository.impl;

import com.sisi.control.model.tasktype.TaskType;
import com.sisi.control.repository.TaskTypeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TaskTypeDao  extends AbstractDao<TaskType, TaskTypeRepository>{
    public TaskTypeDao(TaskTypeRepository taskTypeRepository) {
        super(taskTypeRepository);
    }
}
