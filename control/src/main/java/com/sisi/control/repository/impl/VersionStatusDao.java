package com.sisi.control.repository.impl;

import com.sisi.control.model.version.VersionDto;
import com.sisi.control.model.version.VersionStatistic;
import com.sisi.control.model.version.VersionStatus;
import com.sisi.control.model.version.VersionStatusDto;
import com.sisi.control.repository.VersionStatisticRepository;
import com.sisi.control.repository.VersionStatusRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VersionStatusDao extends AbstractDao<VersionStatus, VersionStatusRepository>{
    protected VersionStatusDao(VersionStatusRepository versionStatusRepository) {
        super(versionStatusRepository);
    }

    public VersionStatusDto save(VersionStatus versionStatus){
       var res = saveDB(versionStatus);
       return res.toDto();
    }

   public VersionStatusDto getById(String id){
        var res = findById(id);
        return res.toDto();
   }

   public List<VersionStatusDto> getList(){
        return findAll().stream().map(i -> i.toDto()).toList();
   }


}
