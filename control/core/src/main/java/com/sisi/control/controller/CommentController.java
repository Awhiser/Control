package com.sisi.control.controller;

import com.sisi.control.model.comment.Comment;
import com.sisi.control.model.comment.CommentDto;
import com.sisi.control.model.response.Response;
import com.sisi.control.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public Response<CommentDto> create(@RequestBody Comment comment){
        var res = commentService.create(comment);
        return Response.success(res);
    }

    @PostMapping("/update")
    public Response<CommentDto> update(@RequestBody Comment comment) {
        var res = commentService.update(comment);
        return Response.success(res);
    }

//    @GetMapping("/get")
//    public Response<Comment> get(@RequestParam String id) {
//        var res = commentService.getById(id);
//        return Response.success(res);
//    }

    @GetMapping("/getByTaskId")
    public Response<List<CommentDto>> getByTaskId(@RequestParam String taskId) {
        var res = commentService.getByTaskId(taskId);
        return Response.success(res);
    }

//    @PostMapping("/getByIds")
//    public Response<List<CommentD>> getByIds(@RequestBody List<String> ids) {
//        var res = commentService.getByIds(ids);
//        return Response.success(res);
//    }


}
