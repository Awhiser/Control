package com.sisi.control.service.version;

import com.sisi.control.model.PageResult;
import com.sisi.control.model.version.*;
import com.sisi.control.repository.impl.VersionDao;
import com.sisi.control.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VersionService {
    private VersionDao versionDao;
    private VersionStatisticService versionStatisticService;
    @Autowired
    VersionService(VersionDao versionDao, VersionStatisticService versionStatisticService) {
        this.versionDao = versionDao;
        this.versionStatisticService = versionStatisticService;
    }


    public VersionDto create(Version version){
        version.setId(CommonUtils.idGenerate());
        version.setCreateTime(new Date());
        var res = versionDao.save(version);
        VersionStatistic statistic = new VersionStatistic();
        statistic.setVersionId(version.getId());
        statistic.setCompleteTask(0);
        statistic.setTotalTask(0);
        versionStatisticService.create(statistic);
        return res;
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

    public PageResult<VersionDetail> getDetailPage(VersionSearchParam versionSearchParam){
        var pageRes =  versionDao.search(versionSearchParam);
        List<String> versionIds = new ArrayList<>();
        //find versionStatistic
        List<VersionDetail> versionDetails = new ArrayList<>();
        pageRes.getDataList().forEach(i->{
            versionIds.add(i.getId());
            VersionDetail versionDetail = new VersionDetail();
            versionDetail.setVersionDto(i);
            versionDetails.add(versionDetail);
        });
        var versionStatisticList =  versionStatisticService.getByVersionIds(versionIds);
        Map<String, VersionStatisticDto> versionScMap = versionStatisticList.stream().collect(Collectors.toMap(i -> i.versionId, v -> v));
        versionDetails.stream().forEach(i->i.setVersionStatistic(versionScMap.getOrDefault(i.versionDto.getId(),null)));
        PageResult<VersionDetail> res = new PageResult<>();
        res.setDataList(versionDetails);
        res.setTotalElement(pageRes.getTotalElement());
        res.setTotalPages(pageRes.getTotalPages());
        return res;
    }

}
