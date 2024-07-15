package com.sisi.control.model.task;

import com.sisi.control.model.AbstractSearch;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class TaskSearchParam extends AbstractSearch {


    public String projectId;
    public String title;



}
