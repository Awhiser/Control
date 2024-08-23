package com.sisi.control.service;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.comment.Comment;
import com.sisi.control.model.comment.CommentDto;
import com.sisi.control.repository.impl.CommentDao;
import com.sisi.control.utils.CommonUtils;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentDao commentDao;

    @Autowired
    public CommentService(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    public CommentDto create(Comment comment){
        comment.setId(CommonUtils.idGenerate());
        return commentDao.save(comment);
    }

    public CommentDto update(Comment comment){
        return commentDao.save(comment);
    }

    public void delete(String id){
         commentDao.deleteById(id);
    }

    public List<CommentDto> getByTaskId(String taskId){
       return commentDao.getByTaskId(taskId);
    }

//    public Comment getById(String id) {
//        return commentDao.findById(id);
//    }
//
//    public List<Comment> getByIds(List<String> ids) {
//        return commentDao.findByIds(ids);
//    }

}
