package com.sisi.control.controller;

import com.sisi.control.model.response.Response;
import com.sisi.control.model.tasklink.TaskLinkType;
import com.sisi.control.service.task.TaskLinkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/taskLinkType")
public class TaskLinkTypeController {
    private TaskLinkTypeService taskLinkTypeService;

    @Autowired
    public TaskLinkTypeController(TaskLinkTypeService taskLinkTypeService){
        this.taskLinkTypeService = taskLinkTypeService;
    }

    @PostMapping("/create")
    public Response create(@RequestBody TaskLinkType link){
        var res = taskLinkTypeService.create(link);
        return Response.success(res);
    }

    @PostMapping("/update")
    public Response update(@RequestBody TaskLinkType link){
        var res = taskLinkTypeService.update(link);
        return Response.success(res);
    }

    @GetMapping("/get")
    public Response get(@RequestParam String id){
        var res = taskLinkTypeService.get(id);
        return Response.success(res);
    }

    @GetMapping("/getAll")
    public Response getAll(){
        var res = taskLinkTypeService.getAll();
        return Response.success(res);
    }

    @GetMapping("/delete")
    public Response delete(String id){
        taskLinkTypeService.delete(id);
        return Response.success();
    }

}
