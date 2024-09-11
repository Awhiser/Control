package com.sisi.control.repository.impl;

import com.sisi.control.model.PageResult;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.task.TaskDto;
import com.sisi.control.model.task.TaskSearchParam;
import com.sisi.control.repository.TaskRepository;
import com.sisi.control.utils.jpatool.JPACondition;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;


@Repository
public class TaskDao extends AbstractDao<Task, TaskRepository>{
    public TaskDao(TaskRepository taskRepository) {
        super(taskRepository);
    }


    public PageResult<TaskDto> getTaskPage(TaskSearchParam param){
        var jpaCondition =  new JPACondition<Task>();
        if(StringUtils.hasText(param.getProjectId())){
            jpaCondition.eq(Task::getProjectId, param.getProjectId() );
        }
        if(StringUtils.hasText(param.getTitle())){
            jpaCondition.like(Task::getTitle, "%" + param.getTitle()+ "%" );
        }
        jpaCondition.sortDesc(Task::getCreateTime);
        var page = findByPage(jpaCondition.build(),param);
        var resData= page.stream().map(i->new TaskDto(i)).toList();
        PageResult<TaskDto> pageRes = new PageResult<>(page);
        pageRes.setDataList(resData);
        return pageRes;
    }

    public TaskDto getById(String id){
        var task = findById(id);
        if(task == null) {
            return new TaskDto();
        }
        TaskDto bo = new TaskDto(task);
        return bo;
    }

    public List<TaskDto> getByIds(List<String> ids){
        var tasks = findByIds(ids);
        return tasks.stream().map(i-> new TaskDto(i)).toList();
    }

    public TaskDto save(Task task){
        var res =  saveDB(task);
        return new TaskDto(res);
    }

}
