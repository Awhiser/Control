package com.sisi.control.model.task;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity
public class CustomField extends AbstractEntity {
    public CustomFieldType type;
    public String name;

}
