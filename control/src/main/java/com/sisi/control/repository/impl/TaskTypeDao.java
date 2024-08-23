package com.sisi.control.repository.impl;

import com.sisi.control.model.tasklink.TaskLink;
import com.sisi.control.model.tasklink.TaskLinkDto;
import com.sisi.control.model.tasktype.TaskType;
import com.sisi.control.model.tasktype.TaskTypeDto;
import com.sisi.control.repository.TaskTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskTypeDao  extends AbstractDao<TaskType, TaskTypeRepository>{
    public TaskTypeDao(TaskTypeRepository taskTypeRepository) {
        super(taskTypeRepository);
    }

    public TaskTypeDto save(TaskType taskType){
        TaskType taskType1 = saveDB(taskType);
        return new TaskTypeDto(taskType1);
    }

    public List<TaskTypeDto> getAll(){
        return findAll().stream().map(i -> new TaskTypeDto(i)).toList();
    }

}
