package com.sisi.control.repository.impl;

import com.sisi.control.model.projectmember.ProjectMember;
import com.sisi.control.model.projectmember.ProjectMemberDto;
import com.sisi.control.repository.ProjectMemberRepository;
import com.sisi.control.utils.jpatool.JPACondition;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectMemberDao extends AbstractDao<ProjectMember, ProjectMemberRepository>{
    public ProjectMemberDao(ProjectMemberRepository projectMemberRepository) {
        super(projectMemberRepository);
    }

    public List<ProjectMemberDto> getByProjectId(String projectId) {
        Specification<ProjectMember> sp = JPACondition.<ProjectMember>builder().eq( ProjectMember::getProjectId, projectId).build();
        return findBySpecification(sp).stream().map(i->new ProjectMemberDto(i)).toList();
    }

    public List<ProjectMemberDto> getByUserId(String userId) {
        Specification<ProjectMember> sp = JPACondition.<ProjectMember>builder().eq(ProjectMember::getUserId, userId).build();
        return findBySpecification(sp).stream().map(i->new ProjectMemberDto(i)).toList();
    }

    public boolean exist(String userId,String projectId){
        var specification =  JPACondition.<ProjectMember>builder()
                .eq(ProjectMember::getProjectId,projectId)
                .eq(ProjectMember::getUserId,userId)
                .build();
        return repo.exists(specification);
    }

    public ProjectMemberDto save(ProjectMember projectMember){
        var res = saveDB(projectMember);
        return new ProjectMemberDto(projectMember);
    }

}
