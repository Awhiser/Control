package com.sisi.control.model.tasklink;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class TaskLinkType extends AbstractEntity {
    public String name;
    public String outName;
    public String inName;

}
