package com.sisi.control.model.taskchangelog;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class TaskChangeLog extends AbstractEntity {

    public String taskId;
    public String userId;
    public String name;
    public String from;
    public String to;
    public Date operateTime;
}
