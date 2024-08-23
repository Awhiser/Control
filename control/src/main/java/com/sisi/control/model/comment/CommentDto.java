package com.sisi.control.model.comment;

public class CommentDto {
    public String id;
    public String taskId;
    public String userId;
    public String title;
    public String content;

    public CommentDto(Comment comment){
        this.id = comment.id;
        this.taskId = comment.taskId;
        this.userId = comment.userId;
        this.title = comment.title;
        this.content = comment.content;
    }

}
