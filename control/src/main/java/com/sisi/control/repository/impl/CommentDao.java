package com.sisi.control.repository.impl;

import com.sisi.control.model.comment.Comment;
import com.sisi.control.repository.CommentRepository;
import com.sisi.control.utils.jpatool.JPACondition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDao extends AbstractDao<Comment, CommentRepository>{
    public CommentDao(CommentRepository commentRepository) {
        super(commentRepository);
    }

    public List<Comment> getByTaskId(String taskId) {
        var sp =  new JPACondition<Comment>().eq(Comment::getTaskId,taskId).build();
        return findBySpecification(sp);
    }
}
