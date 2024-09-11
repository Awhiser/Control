package com.sisi.control.model.task;

import com.sisi.control.model.AbstractEntity;
import lombok.Data;

@Data
//@Entity
public class TaskCustomField extends AbstractEntity {
    public String taskId;
    public String customFieldId;
    public String value;
}
