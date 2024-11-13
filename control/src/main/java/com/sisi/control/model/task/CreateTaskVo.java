package com.sisi.control.model.task;

import com.sisi.control.model.project.ProjectDto;
import com.sisi.control.model.tasktype.TaskType;
import com.sisi.control.model.tasktype.TaskTypeDto;
import com.sisi.control.model.version.VersionDto;
import lombok.Data;

import java.util.List;


@Data
public class CreateTaskVo {

    List<ProjectDto> projectList;
    List<TaskTypeDto> taskTypeList;
    List<VersionDto> versionList;
}
