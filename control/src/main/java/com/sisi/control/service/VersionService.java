package com.sisi.control.service;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.PageView;
import com.sisi.control.model.version.Version;
import com.sisi.control.model.version.VersionSearchParam;
import com.sisi.control.repository.impl.VersionDao;
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


    public Version create(Version version){
        var context = ContextHolder.getContext();
        version.setIsDelete(false);
        version.setUpdateTime(new Date());
        version.setTenantId(context.getTenantId());
        version.setId(context.tenantId + NanoIdUtils.randomNanoId());
        return versionDao.save(version);
    }

    public Version update(Version version) {
        return versionDao.save(version);
    }

    public void delete(String id) {
        versionDao.deleteById(id);
    }

    public Version getById(String id){
        return versionDao.findById(id);
    }

    public List<Version> getByIds(List<String> ids){
        return versionDao.findByIds(ids);
    }

    public PageView<Version> searchVersion(VersionSearchParam versionSearchParam){
        var pageRes =  versionDao.search(versionSearchParam);
        PageView<Version> pageView = new PageView(pageRes);
        pageView.setDataList(pageRes.toList());
        return pageView;
    }


}
