package com.sisi.control.service;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.PageView;
import com.sisi.control.model.ProjectMember.ProjectMember;
import com.sisi.control.model.ProjectMember.ProjectMemberSearchParam;
import com.sisi.control.model.ProjectMember.ProjectMemberVo;
import com.sisi.control.model.user.UserSearchParam;
import com.sisi.control.model.user.UserVo;
import com.sisi.control.repository.impl.ProjectMemberDao;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectMemberService {

    private ProjectMemberDao projectMemberDao;
    private UserService userService;

    @Autowired
    ProjectMemberService(ProjectMemberDao projectMemberDao, UserService userService) {
        this.projectMemberDao = projectMemberDao;
        this.userService = userService;

    }

    public ProjectMember save(ProjectMember projectMember) {
        if(!StringUtils.hasText(projectMember.getId())){
            projectMember.setId( ContextHolder.getContext().tenantId + NanoIdUtils.randomNanoId());
            projectMember.setTenantId(ContextHolder.getContext().getTenantId());
            projectMember.setIsDelete(false);
        }
        return projectMemberDao.save(projectMember);
    }

    public PageView<ProjectMemberVo> getList(ProjectMemberSearchParam searchParam) {
        PageView<ProjectMemberVo> pageView = new PageView<>();
        if (!StringUtils.hasText(searchParam.getProjectId())) {
            return null;
        }
        List<ProjectMember> projectMemberList = projectMemberDao.findByProjectId(searchParam.getProjectId());
        var userIds = projectMemberList.stream().map(i -> i.userId).toList();
        //查询的用户都在项目成员中
        UserSearchParam userSearchParam = new UserSearchParam();
        userSearchParam.setIds(userIds);
        userSearchParam.setPageIndex(searchParam.getPageIndex());
        userSearchParam.setPageSize(searchParam.getPageSize());
        if (StringUtils.hasText(searchParam.getName())) {
            userSearchParam.setName(searchParam.getName());
        }
        var userRes = userService.getUserList(userSearchParam);
        if (userRes.getTotalElement() == 0) {
            return pageView;
        }

        var userMap = userRes.getDataList().stream().collect(Collectors.toMap(i -> i.id, i -> i));

        var members =  projectMemberList.stream()
                .filter(projectMember -> userMap.containsKey(projectMember.getUserId()))
                .map(projectMember -> {
                    ProjectMemberVo vo = new ProjectMemberVo();
                    vo.setId(projectMember.getId());
                    vo.setProjectId(searchParam.getProjectId());
                    vo.setUser( userMap.get(projectMember.getUserId()));
                    return vo;
                }).toList();

       pageView.setDataList(members);
       pageView.setTotalElement(userRes.getTotalElement());
       pageView.setTotalPages(userRes.getTotalPages());
       return pageView;
    }

    @Transactional
    public void delete(String id) {
        projectMemberDao.deleteByIdWithRealRemove(id);
    }


}
