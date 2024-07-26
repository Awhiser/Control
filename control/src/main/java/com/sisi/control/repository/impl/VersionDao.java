package com.sisi.control.repository.impl;

import com.sisi.control.model.PageView;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.tasktype.TaskType;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.model.user.UserSearchParam;
import com.sisi.control.model.version.Version;
import com.sisi.control.model.version.VersionSearchParam;
import com.sisi.control.repository.TaskTypeRepository;
import com.sisi.control.repository.VersionRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VersionDao  extends AbstractDao<Version, VersionRepository>{
    public VersionDao(VersionRepository versionRepository) {
        super(versionRepository);
    }

    public Page<Version> search(VersionSearchParam param){
        Specification<Task> sp = (root, query, builder) ->{
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasText(param.getName())){
                var nameP =builder.like(root.get("name"), "%" + param.getName()+ "%" );
                predicates.add(nameP)  ;
            }
            Predicate[] arr = new Predicate[predicates.size()];
            return builder.and( predicates.toArray(arr) );
        };

        return findByPage(sp,param);
    }
}
