package com.sisi.control.controller;

import com.sisi.control.model.PageResult;
import com.sisi.control.model.response.Response;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.task.TaskSearchParam;
import com.sisi.control.model.task.TaskDto;
import com.sisi.control.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    //...
    @PostMapping("/create")
    public Response<TaskDto> create(@RequestBody Task task) {
        var res = taskService.create(task);
        return Response.success(res);
    }

    @GetMapping("/get")
    public Response<TaskDto> get(@RequestParam String id) {
        var taskVo= taskService.getTask(id);
        return Response.success(taskVo);
    }

    @PostMapping("/getList")
    public Response<PageResult<TaskDto>> getList(@RequestBody TaskSearchParam searchParam){
        var res = taskService.getPage(searchParam);
        return Response.success(res);
    }

    @GetMapping("/getCreateParam")
    public Response getCreateParam(){
        var res = taskService.getCreateParam();
        return Response.success(res);
    }

    @PostMapping("/update")
    public Response<TaskDto> update(@RequestBody Task task) {
        var res = taskService.update(task);
        return Response.success(res);
    }

    @GetMapping("/updateStatus")
    public Response updateStatus(@RequestParam String taskId, String status) {
        taskService.updateStatus(taskId, status);
        return Response.success();
    }

}
