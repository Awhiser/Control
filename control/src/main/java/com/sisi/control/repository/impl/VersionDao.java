package com.sisi.control.repository.impl;

import com.sisi.control.model.PageResult;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.version.Version;
import com.sisi.control.model.version.VersionDto;
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

    public PageResult<VersionDto> search(VersionSearchParam param){

        var builder = JPACondition.<Version>builder();
        if(StringUtils.hasText(param.getName())) {
            builder.like(Version::getName,"%" + param.getName()+ "%");
        }
        var sp =  builder.eq(Version::getProjectId,param.getProjectId()).build();
        var page = findByPage(sp,param);
        PageResult<VersionDto> result = new PageResult<>(page);
        result.setDataList(page.stream().map(i->new VersionDto(i)).toList());
        return result;
    }

    public VersionDto save(Version version){
        var res = saveDB(version);
        return new VersionDto(res);
    }
    public VersionDto getById(String id){
        var res = findById(id);
        return new VersionDto(res);
    }

    public List<VersionDto> getByIds(List<String> ids){
        var res = findByIds(ids);
        return res.stream().map(i-> new VersionDto(i)).toList();
    }
}
