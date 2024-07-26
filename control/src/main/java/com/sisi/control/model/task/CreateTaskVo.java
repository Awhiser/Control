package com.sisi.control.model.task;

import com.sisi.control.model.project.ProjectVo;
import com.sisi.control.model.tasktype.TaskType;
import lombok.Data;

import java.util.List;


@Data
public class CreateTaskVo {

    List<ProjectVo> projectList;
    List<TaskType> taskTypeList;

}
