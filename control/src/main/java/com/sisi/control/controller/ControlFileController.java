package com.sisi.control.controller;

import com.sisi.control.model.response.Response;
import com.sisi.control.service.ControlFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/controlFile")
public class ControlFileController {

    private ControlFileService controlFileService;

    @Autowired
    public ControlFileController(ControlFileService controlFileService) {
        this.controlFileService = controlFileService;
    }

    @PostMapping("/create")
    public Response create(MultipartFile file, String taskId) {
        var res = controlFileService.create(file, taskId);
        return Response.success(res);
    }

    @GetMapping("get")
    public Response getFile(String url){
        var res = controlFileService.getFile(url);
        return Response.success(res) ;
    }


}
