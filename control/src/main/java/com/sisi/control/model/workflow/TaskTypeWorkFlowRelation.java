package com.sisi.control.model.workflow;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class TaskTypeWorkFlowRelation  extends AbstractEntity {

    public String taskTypeId;
    public String workFlowId;

}
