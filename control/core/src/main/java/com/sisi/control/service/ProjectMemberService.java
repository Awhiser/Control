package com.sisi.control.service;

import com.sisi.control.model.PageResult;
import com.sisi.control.model.projectmember.ProjectMember;
import com.sisi.control.model.projectmember.ProjectMemberDto;
import com.sisi.control.model.projectmember.ProjectMemberSearchParam;
import com.sisi.control.model.user.UserSearchParam;
import com.sisi.control.repository.impl.ProjectMemberDao;
import com.sisi.control.utils.CommonUtils;
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

    public ProjectMemberDto save(ProjectMember projectMember) {

        if(!StringUtils.hasText(projectMember.getId())){
            if(projectMemberDao.exist(projectMember.getUserId(), projectMember.getProjectId())){
                return null;
            }
            projectMember.setId(CommonUtils.idGenerate());
        }
        return projectMemberDao.save(projectMember);
    }

    public PageResult<ProjectMemberDto> getPage(ProjectMemberSearchParam searchParam) {
        PageResult<ProjectMemberDto> pageResult = new PageResult<>();
        if (!StringUtils.hasText(searchParam.getProjectId())) {
            return pageResult;
        }
        List<ProjectMemberDto> projectMemberList = projectMemberDao.getByProjectId(searchParam.getProjectId());
        var userIds = projectMemberList.stream().map(i -> i.getUser().getId()).toList();
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
            return pageResult;
        }

        var userMap = userRes.getDataList().stream().collect(Collectors.toMap(i -> i.id, i -> i));

        var members =  projectMemberList.stream()
                .filter(projectMember -> userMap.containsKey(projectMember.getUser().getId()))
                .map(projectMember -> {
                    ProjectMemberDto vo = new ProjectMemberDto();
                    vo.setId(projectMember.getId());
                    vo.setProjectId(searchParam.getProjectId());
                    vo.setUser( userMap.get(projectMember.getUser().getId()));
                    return vo;
                }).toList();

       pageResult.setDataList(members);
       pageResult.setTotalElement(userRes.getTotalElement());
       pageResult.setTotalPages(userRes.getTotalPages());
       return pageResult;
    }

    public List<ProjectMemberDto> getProjectMemberByUserId(String userId) {
        return  projectMemberDao.getByUserId(userId);
    }

    @Transactional
    public void delete(String id) {
        projectMemberDao.deleteByIdWithRealRemove(id);
    }


}
