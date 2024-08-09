package com.sisi.control.service;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.PageView;
import com.sisi.control.model.projectmember.ProjectMember;
import com.sisi.control.model.project.Project;
import com.sisi.control.model.project.ProjectSearchParam;
import com.sisi.control.model.project.ProjectVo;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.repository.impl.ProjectDao;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
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

    public Project save(Project project) {
        String tenantId = ContextHolder.getContext().getTenantId();
        project.setTenantId(tenantId);
        project.setIsDelete(false);
        project.setId(tenantId + NanoIdUtils.randomNanoId());
        return projectDao.save(project);
    }

    public List<Project> getProjectsByIds(List<String> projectIds) {
        return projectDao.getProjectsByIds(projectIds);
    }


    public PageView<ProjectVo> getProjectList(ProjectSearchParam searchParam) {
        var pageRes = projectDao.getProjectList(searchParam);
        List<ProjectVo> projectVos = new ArrayList<>();
        pageRes.stream().forEach(project -> {
            projectVos.add(new ProjectVo(project));
        });


        if(searchParam.isExtLeader()){
            List<String> userIds = pageRes.stream()
                    .filter(i->StringUtils.hasText(i.getLeaderId()))
                    .map(i->i.getLeaderId())
                    .toList();
            var userMap = userService.getUserByIds(userIds).stream().collect(Collectors.toMap(i->i.id,i->i)) ;
            for (var project: projectVos) {
                if (!StringUtils.hasText(project.getLeader().getId())) {
                    continue;
                }
                var user = userMap.getOrDefault(project.getLeader().getId(),new UserInfo());
                project.getLeader().setName(user.getName());
                project.getLeader().setDisplayName(user.getName());
                project.getLeader().setDisplayName(user.getDisplayName());
            }
        }
        PageView<ProjectVo> pageView = new PageView(pageRes);
        pageView.setDataList(projectVos);
        return pageView;
    }

    public List<ProjectVo> getProjectByUserId(String userId) {
        var projectMembers =  projectMemberService.getProjectMemberByUserId(userId);
        if(CollectionUtils.isEmpty(projectMembers)) {
            return new ArrayList<>();
        }
        var projectIds = projectMembers.stream().map(ProjectMember::getProjectId).toList();
        ProjectSearchParam searchParam = new ProjectSearchParam();
        searchParam.setIds(projectIds);
        searchParam.setDisablePage(true);
        var project =  getProjectList(searchParam);
        return project.getDataList();
    }

    public void delete(String id){
        projectDao.deleteById(id);
    }

    public Project update(Project update){
       var  project = projectDao.findById(update.getId());
        project.updateField(update);
       return projectDao.save(project);
    }
}
