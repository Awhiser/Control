package com.sisi.control.repository.impl;

import com.sisi.control.model.PageResult;
import com.sisi.control.model.project.Project;
import com.sisi.control.model.project.ProjectDto;
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

    public PageResult<ProjectDto> getProjectList(ProjectSearchParam param){
        var sp = JPACondition.<Project>builder();
                if(StringUtils.hasText(param.getName()) ){
                    sp.like(Project::getName,"%" + param.getName()+ "%");
                }
        var page =  findByPage(sp.build(),param);
        PageResult<ProjectDto> pageResult = new PageResult(page);
        pageResult.setDataList(page.stream().map(i->new ProjectDto(i)).toList());

        return pageResult;
    }

    public List<ProjectDto> getByIds(List<String> ids){
        return findByIds(ids).stream().map(i->new ProjectDto(i)).toList();
    }

    public ProjectDto save(Project project){
        var res = saveDB(project);
        return new ProjectDto(project);
    }

    public ProjectDto getById(String id) {
        var res = findById(id);
        return new ProjectDto(res);
    }


}
