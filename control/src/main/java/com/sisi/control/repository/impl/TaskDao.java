package com.sisi.control.repository.impl;

import com.sisi.control.model.PageView;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.task.TaskSearchParam;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.repository.TaskRepository;
import com.sisi.control.repository.UserRepository;
import com.sisi.control.utils.log.LogHelper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskDao extends AbstractDao<Task, TaskRepository>{
    public TaskDao(TaskRepository taskRepository) {
        super(taskRepository);
    }


    public Page<Task> getTaskList(TaskSearchParam param){
        Specification<Task> sp = (root,query,builder) ->{
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasText(param.getId())){
                Predicate predicate = builder.equal(root.get("id"), param.getId());
                predicates.add(predicate);
            }
            if(StringUtils.hasText(param.getProjectId())){
                Predicate predicate = builder.equal(root.get("projectId"), param.getProjectId());
                predicates.add(predicate);
            }
            if(StringUtils.hasText(param.getTitle())){
                Predicate predicate = builder.like(root.get("title"), param.getTitle());
                predicates.add(predicate);
            }
            Predicate[] arr = new Predicate[predicates.size()];
            return builder.and( predicates.toArray(arr) );
        };


        return findByPage(sp,param.getPageRequest());
    }
}
