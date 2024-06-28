package com.sisi.control.model.version;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Version extends AbstractEntity {
    public String name;
    public String status;
    public String projectId;


}
