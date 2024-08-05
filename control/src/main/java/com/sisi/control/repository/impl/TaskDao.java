package com.sisi.control.repository.impl;

import com.sisi.control.model.task.Task;
import com.sisi.control.model.task.TaskSearchParam;
import com.sisi.control.repository.TaskRepository;
import com.sisi.control.utils.jpatool.JPACondition;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


@Repository
public class TaskDao extends AbstractDao<Task, TaskRepository>{
    public TaskDao(TaskRepository taskRepository) {
        super(taskRepository);
    }


    public Page<Task> getTaskList(TaskSearchParam param){
        var jpaCondition =  new JPACondition<Task>();
        if(StringUtils.hasText(param.getProjectId())){
            jpaCondition.eq(Task::getProjectId, param.getProjectId() );
        }
        if(StringUtils.hasText(param.getTitle())){
            jpaCondition.like(Task::getTitle, "%" + param.getTitle()+ "%" );
        }
        return findByPage(jpaCondition.build(),param);
    }
}
