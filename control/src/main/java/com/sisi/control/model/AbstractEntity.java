package com.sisi.control.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass//实体继承映射
public class AbstractEntity {
    @Id
    public String id;
    public String tenantId;
    public Boolean isDelete;
}
