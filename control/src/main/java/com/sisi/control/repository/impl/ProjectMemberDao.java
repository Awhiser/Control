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

    public List<ProjectMember> findByProjectId(String projectId) {
        Specification<ProjectMember> specification = (root, query, builder) -> {
                Predicate predicate = builder.equal(root.get("projectId"),  projectId);
                return builder.and(predicate);
        };

        return findBySpecification(specification);
    }

}
