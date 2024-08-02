package com.sisi.control.model.tasklink;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class TaskLink extends AbstractEntity {
    public TaskLinkEnum linkType;
    public String linkName;
    public String description;
    public String url;
    public String taskId;
}
