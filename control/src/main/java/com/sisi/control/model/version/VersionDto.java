package com.sisi.control.model.version;

import lombok.Data;

import java.util.Date;
@Data
public class VersionDto {
    public String id;
    public String name;
    public String status;
    public String description;
    public Date updateTime;
    public String projectId;

    public VersionDto(){

    }

    public VersionDto(Version version) {
        this.id = version.id;
        this.name = version.name;

        //this.status = version.status;
        this.description = version.description;
        this.updateTime = version.updateTime;
        this.projectId = version.projectId;
    }


}
