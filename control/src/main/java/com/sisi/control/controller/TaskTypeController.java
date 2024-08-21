package com.sisi.control.controller;

import com.sisi.control.model.response.Response;
import com.sisi.control.model.tasktype.TaskType;
import com.sisi.control.service.task.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taskType")
public class TaskTypeController {
    private TaskTypeService taskTypeService;

    @Autowired
    TaskTypeController(TaskTypeService taskTypeService){
        this.taskTypeService = taskTypeService;
    }

    @PostMapping("/create")
    public Response create(@RequestBody TaskType type){
        var res =  taskTypeService.create(type);
        return Response.success(res);
    }

    @PostMapping("/update")
    public Response update(TaskType type){
        taskTypeService.update(type);
        return Response.success();
    }

    @GetMapping("/delete")
    public Response delete(String id){
        taskTypeService.delete(id);
        return Response.success();
    }

    @GetMapping("/getList")
    public Response<List<TaskType>> getList(){
        var res = taskTypeService.getList();
        return Response.success(res);
    }






}
