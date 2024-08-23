package com.sisi.control.controller;

import com.sisi.control.model.PageResult;
import com.sisi.control.model.response.Response;
import com.sisi.control.model.version.Version;
import com.sisi.control.model.version.VersionDto;
import com.sisi.control.model.version.VersionSearchParam;
import com.sisi.control.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/version")
public class VersionController {

    private VersionService versionService;

    @Autowired
    public VersionController(VersionService versionService){
        this.versionService = versionService;
    }

    @PostMapping("/create")
    public Response<VersionDto> create(@RequestBody Version version){
        var res = versionService.create(version);
        return Response.success(res);
    }

    @PostMapping("/getList")
    public Response<PageResult<VersionDto>> getList(@RequestBody VersionSearchParam param){
        var res = versionService.searchVersion(param);
        return Response.success(res);
    }

    @GetMapping("/get")
    public Response<PageResult<VersionDto>> get(@RequestParam String id){
        var res = versionService.getById(id);
        return Response.success(res);
    }

    @PostMapping("/update")
    public Response<VersionDto> update(@RequestBody Version version){
        var res = versionService.update(version);
        return Response.success(res);
    }

    @GetMapping("/delete")
    public Response delete(@RequestParam String id) {
        versionService.delete(id);
        return Response.success();
    }

}
