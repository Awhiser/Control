package com.sisi.control.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass//实体继承映射
public class AbstractEntity {
    @Id
    public String id;
    public String tenantId;
    public Boolean isDelete;
    public Date createTime;
    public Date updateTime;
}
