package com.sisi.control.model.task;

import com.sisi.control.model.AbstractSearch;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
public class TaskSearchParam extends AbstractSearch {


    public String projectId;
    public String title;
    public List<String> type;
    public List<String> assignee;
    public List<String> tags;
    public List<String> priority;
    public List<String> status;

}
