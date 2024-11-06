package com.sisi.control.model.version;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
public class VersionStatistic extends AbstractEntity {
    public String versionId;
    public long totalTask;
    public long completeTask;


    public  VersionStatistic(){

    }

    public  VersionStatistic(VersionStatisticDto versionStatisticDto){
            this.id = versionStatisticDto.getId();
            this.versionId = versionStatisticDto.getVersionId();
            this.totalTask = versionStatisticDto.getTotalTask();
            this.completeTask = versionStatisticDto.getCompleteTask();
    }

}
