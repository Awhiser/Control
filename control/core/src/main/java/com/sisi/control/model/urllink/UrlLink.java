package com.sisi.control.model.urllink;

import com.sisi.control.model.AbstractEntity;
import lombok.Data;

@Data
public class UrlLink  extends AbstractEntity {
    public String taskId;
    public String description;
    public String url;
}
