package com.sisi.control.service;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.comment.Comment;
import com.sisi.control.model.comment.CommentDto;
import com.sisi.control.model.user.UserInfoDto;
import com.sisi.control.repository.impl.CommentDao;
import com.sisi.control.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentDao commentDao;
    private UserService userService;

    @Autowired
    public CommentService(CommentDao commentDao,UserService userService){
        this.commentDao = commentDao;
        this.userService = userService;
    }

    public CommentDto create(Comment comment){
        comment.setId(CommonUtils.idGenerate());
        comment.setUserId(ContextHolder.getContext().getToken().userId);
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        return commentDao.save(comment);
    }

    public CommentDto update(Comment comment){
        return commentDao.save(comment);
    }

    public void delete(String id){
         commentDao.deleteById(id);
    }

    public List<CommentDto> getByTaskId(String taskId){
       var commentList = commentDao.getByTaskId(taskId);
       if(CollectionUtils.isEmpty(commentList)){
           return commentList;
       }

       var userIds = commentList.stream().map(i->i.user.id).toList();
        Map<String, UserInfoDto> userMap = userService.getUserByIds(userIds).stream().collect(Collectors.toMap(k -> k.getId(), v -> v));
        for(var comment : commentList){
            if(userMap.containsKey(comment.getUser().getId())) {
                comment.setUser(userMap.get(comment.getUser().getId()));
            }
        }

        return commentList;

    }

//    public Comment getById(String id) {
//        return commentDao.findById(id);
//    }
//
//    public List<Comment> getByIds(List<String> ids) {
//        return commentDao.findByIds(ids);
//    }

}
