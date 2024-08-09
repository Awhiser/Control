package com.sisi.control.model.controlfile;

import com.sisi.control.model.AbstractEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ControlFile extends AbstractEntity {
    public String name;
    public String url;
    public String taskId;
    public Date createTime;


    public String generatePath() {
        return tenantId+"/";
    }
}
