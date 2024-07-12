package com.sisi.control.repository.impl;

import com.sisi.control.model.project.Project;
import com.sisi.control.model.project.ProjectSearchParam;
import com.sisi.control.model.project.ProjectVo;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.user.UserConnector;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.model.user.UserSearchParam;
import com.sisi.control.repository.ProjectRepository;
import com.sisi.control.repository.UserConnectorRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectDao extends AbstractDao<Project, ProjectRepository>{
    public ProjectDao(ProjectRepository projectRepository) {
        super(projectRepository);
    }

    public Page<Project> getProjectList(ProjectSearchParam param){
        Specification<Task> sp = (root, query, builder) ->{
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasText(param.getId())){
                Predicate predicate = builder.equal(root.get("id"), param.getId());
                predicates.add(predicate);
            }
            if(StringUtils.hasText(param.getName())){
                predicates.add(builder.like(root.get("name"), "%" + param.getName()+ "%" ));
            }
            Predicate[] arr = new Predicate[predicates.size()];
            return builder.and( predicates.toArray(arr) );
        };

        return findByPage(sp,param.getPageRequest());
    }



}
