package com.sisi.control.service.version;

import com.sisi.control.model.task.Task;
import com.sisi.control.model.version.VersionStatistic;
import com.sisi.control.model.version.VersionStatisticDto;
import com.sisi.control.repository.impl.VersionDao;
import com.sisi.control.repository.impl.VersionStatisticDao;
import com.sisi.control.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class VersionStatisticService {

    private VersionStatisticDao versionStatisticDao;

    VersionStatisticService(VersionStatisticDao versionStatisticDao) {
        this.versionStatisticDao = versionStatisticDao;
    }


    public List<VersionStatisticDto> getByIds(List<String> ids) {
        return versionStatisticDao.getByIds(ids);
    }

    public VersionStatisticDto create(VersionStatistic versionStatistic) {
        versionStatistic.setId(CommonUtils.idGenerate());
        versionStatistic.setCreateTime(new Date());
        return versionStatisticDao.save(versionStatistic);
    }

    public List<VersionStatisticDto> getByVersionIds(List<String> ids) {
        return versionStatisticDao.getByVersionIds(ids);
    }

    //
    public void update(String versionId , long count){
        var versionStatistic = versionStatisticDao.getByVersionId(versionId);
        versionStatistic.totalTask += count;
        versionStatisticDao.save(new VersionStatistic(versionStatistic) );
    }




}
