package com.sisi.control.control;

import com.sisi.control.model.PageView;
import com.sisi.control.model.response.Response;
import com.sisi.control.model.task.TaskCreateParam;
import com.sisi.control.model.task.TaskSearchParam;
import com.sisi.control.model.task.TaskVo;
import com.sisi.control.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskControl {

    private TaskService taskService;

    @Autowired
    public TaskControl(TaskService taskService){
        this.taskService = taskService;
    }

    //
    @PostMapping("/create")
    public Response<TaskVo> create(@RequestBody TaskCreateParam taskCreateParam) {
        return taskService.create(taskCreateParam);
    }

    @GetMapping("/get")
    public Response<TaskVo> get(@RequestParam String id) {
        var taskVo= taskService.getTask(id);
        return Response.success(taskVo);
    }

    @PostMapping("/getList")
    public Response<PageView<TaskVo>> getList(@RequestBody TaskSearchParam searchParam){
        var res = taskService.getTaskList(searchParam);
        return Response.success(res);
    }

}
