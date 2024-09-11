package com.sisi.control.model.projectmember;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class ProjectMember extends AbstractEntity {
    public String id;
    public String userId;
    public String projectId;

}
