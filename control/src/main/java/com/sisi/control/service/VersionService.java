package com.sisi.control.service;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.PageResult;
import com.sisi.control.model.version.Version;
import com.sisi.control.model.version.VersionDto;
import com.sisi.control.model.version.VersionSearchParam;
import com.sisi.control.repository.impl.VersionDao;
import com.sisi.control.utils.CommonUtils;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VersionService {
    private VersionDao versionDao;
    VersionService(VersionDao versionDao) {
        this.versionDao = versionDao;
    }


    public VersionDto create(Version version){
        version.setId(CommonUtils.idGenerate());
        version.setCreateTime(new Date());
        return versionDao.save(version);
    }

    public VersionDto update(Version version) {
        return versionDao.save(version);
    }

    public void delete(String id) {
        versionDao.deleteById(id);
    }

    public VersionDto getById(String id){
        return versionDao.getById(id);
    }

    public List<VersionDto> getByIds(List<String> ids){
        return versionDao.getByIds(ids);
    }

    public PageResult<VersionDto> searchVersion(VersionSearchParam versionSearchParam){
        var pageRes =  versionDao.search(versionSearchParam);
        return pageRes;
    }


}
