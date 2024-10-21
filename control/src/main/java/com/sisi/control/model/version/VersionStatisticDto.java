package com.sisi.control.model.version;

import lombok.Data;

@Data
public class VersionStatisticDto {
    public String id;
    public String versionId;
    public long totalTask;
    public long completeTask;

    public VersionStatisticDto() {

    }

    public VersionStatisticDto(VersionStatistic versionStatistic) {
        this.id = versionStatistic.getId();
        this.versionId = versionStatistic.getVersionId();
        this.totalTask = versionStatistic.getTotalTask();
        this.completeTask = versionStatistic.getCompleteTask();
    }

}
