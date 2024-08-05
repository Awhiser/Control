package com.sisi.control.repository.impl;

import com.sisi.control.model.task.Task;
import com.sisi.control.model.version.Version;
import com.sisi.control.model.version.VersionSearchParam;
import com.sisi.control.repository.VersionRepository;
import com.sisi.control.utils.jpatool.JPACondition;
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
        var sp = JPACondition.<Version>builder().like(Version::getName,"%" + param.getName()+ "%").build();
        return findByPage(sp,param);
    }
}
