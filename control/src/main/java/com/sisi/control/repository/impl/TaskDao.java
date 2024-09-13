package com.sisi.control.repository.impl;

import com.sisi.control.model.PageResult;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.task.TaskDto;
import com.sisi.control.model.task.TaskSearchParam;
import com.sisi.control.repository.TaskRepository;
import com.sisi.control.utils.jpatool.JPACondition;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

@Repository
public class TaskDao extends AbstractDao<Task, TaskRepository>{
    public TaskDao(TaskRepository taskRepository) {
        super(taskRepository);
    }


    public PageResult<TaskDto> getTaskPage(TaskSearchParam param) {
        var jpaCondition = new JPACondition<Task>();
        if (StringUtils.hasText(param.getProjectId())) {
            jpaCondition.eq(Task::getProjectId, param.getProjectId());
        }
        if (StringUtils.hasText(param.getTitle())) {
            jpaCondition.like(Task::getTitle, "%" + param.getTitle() + "%");
        }

        if (!CollectionUtils.isEmpty(param.getAssignee())) {
            jpaCondition.in(Task::getAssignee, param.getAssignee());
        }
        if (!CollectionUtils.isEmpty(param.getPriority())) {
            jpaCondition.in(Task::getPriority, param.getPriority());
        }
        if (!CollectionUtils.isEmpty(param.getStatus())) {
            jpaCondition.in(Task::getStatus, param.getStatus());
        }
        if (!CollectionUtils.isEmpty(param.getType())) {
            jpaCondition.in(Task::getType, param.getType());
        }
        jpaCondition.sortDesc(Task::getCreateTime);

        var sp = jpaCondition.build();

        if (!CollectionUtils.isEmpty(param.getTags())) {
            Specification orSp = (root, query, builder)->{
                List<Predicate> predicates = new ArrayList<>();
                for (int i = 0; i < param.getTags().size() ; i++) {
                 builder.like(root.get("tags"), "%"+ param.getTags().get(i) +"%" );
                }
                return builder.or(predicates.toArray(new Predicate[]{}));

            };
            sp = orSp.and(sp);
        }


        var page = findByPage(sp, param);
        var resData = page.stream().map(i -> new TaskDto(i)).toList();
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

    @Transactional
    public void updateStatus(String taskId, String status) {
        String sql  = "UPDATE task SET status = '"+status+"' WHERE id = '"+taskId+"'";
        executeUpdateSql(sql);
    }


}
