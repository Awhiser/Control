package com.sisi.control.controller;

import com.sisi.control.model.response.Response;
import com.sisi.control.model.taskchangelog.TaskChangeLogDto;
import com.sisi.control.service.task.TaskChangeLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/taskChangeLog")
public class TaskChangeLogController {

    private TaskChangeLogService taskChangeLogService;

    public TaskChangeLogController(TaskChangeLogService taskChangeLogService){
        this.taskChangeLogService = taskChangeLogService;
    }

    @GetMapping("/getByTaskId")
    public Response<List<TaskChangeLogDto>> get(@RequestParam String taskId) {
        var res= taskChangeLogService.getByTaskId(taskId);
        return Response.success(res);
    }
}
