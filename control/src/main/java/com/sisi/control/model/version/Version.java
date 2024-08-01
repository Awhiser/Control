package com.sisi.control.model.version;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Version extends AbstractEntity {
    public String name;
    public String status;
    public String description;
    public Date updateTime;
    public String projectId;


}
