package com.sisi.control.repository.impl;

import com.sisi.control.model.project.Project;
import com.sisi.control.model.project.ProjectSearchParam;
import com.sisi.control.repository.ProjectRepository;
import com.sisi.control.utils.jpatool.JPACondition;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class ProjectDao extends AbstractDao<Project, ProjectRepository>{
    public ProjectDao(ProjectRepository projectRepository) {
        super(projectRepository);
    }

    public Page<Project> getProjectList(ProjectSearchParam param){
        var sp = JPACondition.<Project>builder()
                .like(Project::getName,"%" + param.getName()+ "%")
                .build();
        return findByPage(sp,param);
    }

    public List<Project> getProjectsByIds(List<String> ids){
        return findByIds(ids);
    }



}
