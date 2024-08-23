package com.sisi.control.repository.impl;


import com.sisi.control.model.task.TaskDto;
import com.sisi.control.model.tasklink.TaskLink;
import com.sisi.control.model.tasklink.TaskLinkDto;
import com.sisi.control.repository.TaskLinkRepository;

import com.sisi.control.utils.jpatool.JPACondition;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TaskLinkDao extends AbstractDao<TaskLink, TaskLinkRepository> {
    public TaskLinkDao(TaskLinkRepository taskLinkRepository) {
        super(taskLinkRepository);
    }

    public List<TaskLinkDto> getByTaskId(String taskId){
        Specification sp =  JPACondition.<TaskLink>builder()
                .eq(TaskLink::getId,taskId)
                .build();
        return findBySpecification(sp).stream().map(i-> new TaskLinkDto(i)).toList();
    }

    public TaskLinkDto save(TaskLink taskLink){
        TaskLink taskLink1 = saveDB(taskLink);
        return new TaskLinkDto(taskLink1);
    }

    public TaskLinkDto getById(String id){
        var res = findById(id);
        return new TaskLinkDto(res);
    }

}
