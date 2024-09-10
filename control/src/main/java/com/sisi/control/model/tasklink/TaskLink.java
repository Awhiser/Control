package com.sisi.control.model.tasklink;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class TaskLink extends AbstractEntity {
    public String outTaskId;
    public String linkId;
    public String inTaskId;
}
