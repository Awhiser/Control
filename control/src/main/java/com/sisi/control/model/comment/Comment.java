package com.sisi.control.model.comment;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Comment extends AbstractEntity {
    public String taskId;
    public String userId;
    public String title;
    public String content;
}
