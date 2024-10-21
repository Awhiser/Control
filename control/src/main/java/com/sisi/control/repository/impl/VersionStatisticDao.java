package com.sisi.control.repository.impl;

import com.sisi.control.model.version.Version;
import com.sisi.control.model.version.VersionDto;
import com.sisi.control.model.version.VersionStatistic;
import com.sisi.control.model.version.VersionStatisticDto;
import com.sisi.control.repository.VersionRepository;
import com.sisi.control.repository.VersionStatisticRepository;
import com.sisi.control.utils.jpatool.JPACondition;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VersionStatisticDao  extends AbstractDao<VersionStatistic, VersionStatisticRepository>{
    protected VersionStatisticDao(VersionStatisticRepository versionStatisticRepository) {
        super(versionStatisticRepository);
    }


    public List<VersionStatisticDto> getByIds(List<String> ids){
        var res =  findByIds(ids);
        return  res.stream().map(i-> new VersionStatisticDto(i)).toList();
    }

    public VersionStatisticDto save(VersionStatistic versionStatistic) {
        var res = saveDB(versionStatistic);
        return new VersionStatisticDto(res);
    }

    public List<VersionStatisticDto> getByVersionIds(List<String> ids) {

        Specification<VersionStatistic> sp = JPACondition.<VersionStatistic>builder().in(VersionStatistic::getVersionId, ids).build();
        return findBySpecification(sp).stream().map(i->new VersionStatisticDto(i)).toList();

    }
}
