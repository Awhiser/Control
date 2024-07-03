package com.sisi.control.control;

import com.sisi.control.model.project.Project;
import com.sisi.control.model.project.ProjectSearchParam;
import com.sisi.control.model.response.Response;
import com.sisi.control.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
public class ProjectControl {

    private ProjectService projectService;

    @Autowired
    public ProjectControl(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping("/save")
    public Response save(@RequestBody Project project){
        projectService.save(project);
        return Response.success();
    }


    @PostMapping("/getList")
    public Response getList(@RequestBody ProjectSearchParam searchParam){
        var res =  projectService.getProjectList(searchParam);
        return Response.success(res);
    }

    @GetMapping("/delete")
    public Response delete(@RequestParam String id){
        projectService.delete(id);
        return Response.success();

    }

    @PostMapping("/update")
    public Response update(@RequestBody Project project){
        var res = projectService.update(project);
        return Response.success(res);

    }


}
