package com.sisi.control.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

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
