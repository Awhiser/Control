package com.sisi.control.repository.impl;


import com.sisi.control.model.tasklink.TaskLink;
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

    public List<TaskLink> getByTaskId(String taskId){
        Specification sp =  JPACondition.<TaskLink>builder()
                .eq(TaskLink::getId,taskId)
                .build();
        return findBySpecification(sp);
    }
}
