package com.sisi.control.service;


import com.sisi.control.model.PageResult;
import com.sisi.control.model.project.Project;
import com.sisi.control.model.project.ProjectDto;
import com.sisi.control.model.project.ProjectSearchParam;
import com.sisi.control.model.projectmember.ProjectMemberDto;
import com.sisi.control.model.user.UserInfoDto;
import com.sisi.control.repository.impl.ProjectDao;
import com.sisi.control.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private ProjectDao projectDao;

    private UserService userService;

    private ProjectMemberService projectMemberService;

    @Autowired
    public ProjectService(ProjectDao projectDao, UserService userService, ProjectMemberService projectMemberService) {
        this.projectDao = projectDao;
        this.userService = userService;
        this.projectMemberService = projectMemberService;
    }

    public ProjectDto save(Project project) {
        project.setId(CommonUtils.idGenerate());
        return projectDao.save(project);
    }



    public PageResult<ProjectDto> getProjectPage(ProjectSearchParam searchParam) {
        var pageRes = projectDao.getProjectList(searchParam);
        if(searchParam.isExtLeader()){
            List<String> userIds = pageRes.getDataList().stream()
                    .filter(i->StringUtils.hasText(i.getLeader().getId()))
                    .map(i->i.getLeader().getId())
                    .toList();
            var userMap = userService.getUserByIds(userIds).stream().collect(Collectors.toMap(i->i.id,i->i)) ;
            for (var project: pageRes.getDataList()) {
                if (!StringUtils.hasText(project.getLeader().getId())) {
                    continue;
                }
                var user = userMap.getOrDefault(project.getLeader().getId(),new UserInfoDto());
                project.getLeader().setName(user.getName());
                project.getLeader().setDisplayName(user.getName());
                project.getLeader().setDisplayName(user.getDisplayName());
            }
        }
        return pageRes;
    }

    public List<ProjectDto> getProjectByUserId(String userId) {
        var projectMembers =  projectMemberService.getProjectMemberByUserId(userId);
        if(CollectionUtils.isEmpty(projectMembers)) {
            return new ArrayList<>();
        }
        var projectIds = projectMembers.stream().map(ProjectMemberDto::getProjectId).toList();
        var projects = getByIds(projectIds);
        return projects;
    }

    public void delete(String id){
        projectDao.deleteById(id);
    }

    //todo 修改前端传值
    public ProjectDto update(Project project){
       return projectDao.save(project);
    }

    public List<ProjectDto> getByIds(List<String> ids){
        return projectDao.getByIds(ids);
    }

    public ProjectDto getById(String id) {
        return projectDao.getById(id);
    }

}
