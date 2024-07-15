package com.sisi.control.repository.impl;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.ProjectMember.ProjectMember;
import com.sisi.control.repository.ProjectMemberRepository;
import com.sisi.control.repository.TaskRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectMemberDao extends AbstractDao<ProjectMember, ProjectMemberRepository>{
    public ProjectMemberDao(ProjectMemberRepository projectMemberRepository) {
        super(projectMemberRepository);
    }

    public List<ProjectMember> getByProjectId(String projectId) {
        Specification<ProjectMember> specification = (root, query, builder) -> {
                Predicate predicate = builder.equal(root.get("projectId"),  projectId);
                return builder.and(predicate);
        };

        return findBySpecification(specification);
    }

    public List<ProjectMember> getByUserId(String userId) {
        Specification<ProjectMember> specification = (root, query, builder) -> {
            Predicate predicate = builder.equal(root.get("userId"),  userId);
            return builder.and(predicate);
        };

        return findBySpecification(specification);
    }

    public boolean exist(String userId,String projectId){
        Specification<ProjectMember> specification = (root, query, builder) -> {
            Predicate predicate = builder.equal(root.get("projectId"),  projectId);
            Predicate predicate2 = builder.equal(root.get("userId"),  userId);
            return builder.and(predicate,predicate2);
        };
        return repo.exists(specification);
    }

}
