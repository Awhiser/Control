package com.sisi.control.model.controlfile;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class ControlFile extends AbstractEntity {
    public String name;
    public String url;
    public String taskId;
    public Date createTime;


    public String generatePath() {
        return tenantId+"/";
    }
}
