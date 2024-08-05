package com.sisi.control.controller;

import com.sisi.control.model.comment.Comment;
import com.sisi.control.model.response.Response;
import com.sisi.control.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public Response<Comment> create(@RequestBody Comment comment){
        var res = commentService.create(comment);
        return Response.success(res);
    }

    @PostMapping("/update")
    public Response<Comment> update(@RequestBody Comment comment) {
        var res = commentService.update(comment);
        return Response.success(res);
    }

    @GetMapping("/get")
    public Response<Comment> get(@RequestParam String id) {
        var res = commentService.getById(id);
        return Response.success(res);
    }

    @GetMapping("/getByTaskId")
    public Response<List<Comment>> getByTaskId(@RequestParam String taskId) {
        var res = commentService.getByTaskId(taskId);
        return Response.success(res);
    }


}
