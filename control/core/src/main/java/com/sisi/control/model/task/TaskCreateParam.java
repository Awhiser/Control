package com.sisi.control.model.task;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskCreateParam {
    public String title;
    public String assignee;
    public String type;
 //   public String versionId;
    public String priority;
    public List<String> tags;
    public String description;
    public Date duedate;
    public String projectId;
    public String parentId;
    // id - value
  //  public Map<String,String> customFields;
}
