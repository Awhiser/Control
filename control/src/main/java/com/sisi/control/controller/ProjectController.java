package com.sisi.control.controller;

import com.sisi.control.model.PageView;
import com.sisi.control.model.project.ProjectVo;
import com.sisi.control.model.projectmember.ProjectMember;
import com.sisi.control.model.projectmember.ProjectMemberSearchParam;
import com.sisi.control.model.project.Project;
import com.sisi.control.model.project.ProjectSearchParam;
import com.sisi.control.model.response.Response;
import com.sisi.control.service.ProjectMemberService;
import com.sisi.control.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectService projectService;
    private ProjectMemberService projectMemberService;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectMemberService projectMemberService){
        this.projectService = projectService;
        this.projectMemberService = projectMemberService;
    }

    @PostMapping("/save")
    public Response save(@RequestBody Project project){
        projectService.save(project);
        return Response.success();
    }

    @GetMapping("/project")
    public Response getProjectById(@RequestParam String id){
        var res = projectService.getById(id);
        return Response.success(res);

    }


    @PostMapping("/getProjectPage")
    public Response getProjectPage(@RequestBody ProjectSearchParam searchParam){
        var res =  projectService.getProjectPage(searchParam);
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

    @GetMapping("/getProjectByUserId")
    public Response<List<ProjectVo>> getProjectByUserId(@RequestParam String userId){
       var res =  projectService.getProjectByUserId(userId);
       return Response.success(res);
    }

    @PostMapping("/projectMemmber/save")
    public Response saveProjectMember(@RequestBody ProjectMember projectMember){
        var res =  projectMemberService.save(projectMember);
        return Response.success(res);
    }

    @PostMapping("/projectMemmber/getList")
    public Response<PageView<ProjectMember>> getProjectMemberList(@RequestBody ProjectMemberSearchParam projectMemberSearchParam){
        var res =  projectMemberService.getList(projectMemberSearchParam);
        return Response.success(res);
    }

    @GetMapping("/projectMemmber/delete")
    public Response deleteProjectMemmber(@RequestParam String id){
         projectMemberService.delete(id);
        return Response.success();
    }

}
