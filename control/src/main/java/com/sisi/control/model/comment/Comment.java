package com.sisi.control.model.comment;

import com.sisi.control.model.AbstractEntity;
import lombok.Data;

@Data
public class Comment extends AbstractEntity {
    public String taskId;
    public String userId;
    public String title;
    public String content;
}
