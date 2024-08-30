package com.sisi.control.repository.impl;

import com.sisi.control.model.tasklink.TaskLinkType;
import com.sisi.control.model.tasklink.TaskLinkTypeDto;
import com.sisi.control.repository.TaskLinkTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskLinkTypeDao extends AbstractDao<TaskLinkType, TaskLinkTypeRepository> {
    protected TaskLinkTypeDao(TaskLinkTypeRepository taskLinkTypeRepository) {
        super(taskLinkTypeRepository);
    }

    public TaskLinkTypeDto save(TaskLinkType type){
        var res = saveDB(type);
        return new TaskLinkTypeDto(res);
    }

    public TaskLinkTypeDto get(String id){
        var res = findById(id);
        return new TaskLinkTypeDto(res);
    }

    public List<TaskLinkTypeDto> getAll(){
        var res = findAll();
        return res.stream().map(TaskLinkTypeDto::new).toList();
    }




}
