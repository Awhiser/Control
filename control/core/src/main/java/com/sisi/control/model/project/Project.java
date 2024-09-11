package com.sisi.control.model.project;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
@Entity
public class Project extends AbstractEntity {
    String name;
    String leaderId;


    public void updateField(Project update) {
        if (StringUtils.hasText(update.getLeaderId())) {
            this.leaderId = update.getLeaderId();
        }
        if (StringUtils.hasText(update.getName())) {
            this.name = update.getName();
        }
    }
}
