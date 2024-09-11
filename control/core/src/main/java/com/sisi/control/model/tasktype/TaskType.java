package com.sisi.control.model.tasktype;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class TaskType extends AbstractEntity {
        public String name;
        public String img;
}
