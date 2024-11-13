package com.sisi.control.service.version;

import com.sisi.control.model.version.VersionStatus;
import com.sisi.control.model.version.VersionStatusDto;
import com.sisi.control.repository.impl.VersionDao;
import com.sisi.control.repository.impl.VersionStatusDao;
import com.sisi.control.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionStatusService {
    private VersionStatusDao versionStatusDao;

    @Autowired
    VersionStatusService(VersionStatusDao versionStatusDao) {
        this.versionStatusDao = versionStatusDao;
    }

    public VersionStatusDto create(VersionStatus versionStatus) {
        versionStatus.setId(CommonUtils.idGenerate());
        return versionStatusDao.save(versionStatus);
    }

    public VersionStatusDto update(VersionStatus versionStatus) {
        return versionStatusDao.save(versionStatus);
    }

    public VersionStatusDto getById(String id) {
        return versionStatusDao.getById(id);
    }

    public List<VersionStatusDto> getList(){
        return versionStatusDao.getList();
    }

}
