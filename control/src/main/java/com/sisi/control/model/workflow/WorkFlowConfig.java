package com.sisi.control.model.workflow;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class WorkFlowConfig extends AbstractEntity {

    //名称
    public String name;

    //workflow请求 http://www.workflow.com:8080/createWorkflow
    public String createUrl;

    //返回流程图信息
    public String imgUrl;

    //返回流程状态
    public String status;



}
