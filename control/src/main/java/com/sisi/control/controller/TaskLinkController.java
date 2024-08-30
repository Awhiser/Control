package com.sisi.control.controller;

import com.sisi.control.model.response.Response;
import com.sisi.control.model.tasklink.TaskLink;
import com.sisi.control.service.task.TaskLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/taskLink")
public class TaskLinkController {


    private TaskLinkService taskLinkService;

    @Autowired
    public TaskLinkController(TaskLinkService taskLinkService){
        this.taskLinkService = taskLinkService;
    }

    @PostMapping("/create")
    public Response create(@RequestBody TaskLink link){
        var res = taskLinkService.create(link);
        return Response.success(res);
    }

    @GetMapping("/get")
    public Response get(@RequestParam String id){
        var res = taskLinkService.getById(id);
        return Response.success(res);
    }

    @GetMapping("/getByTaskId")
    public Response getByTaskId(@RequestParam String taskId){
        var res = taskLinkService.getByTaskId(taskId);
        return Response.success(res);
    }

    @GetMapping("/delete")
    public Response delete(String id){
        taskLinkService.deleteById(id);
        return Response.success();
    }







}
