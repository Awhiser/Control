package com.sisi.control.model.comment;

import com.sisi.control.model.user.UserInfoDto;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    public String id;
    public String taskId;
    public UserInfoDto user;
    public String title;
    public String content;
    public Date createTime;

    public CommentDto(){

    }

    public CommentDto(Comment comment){
        this.id = comment.id;
        this.taskId = comment.taskId;
        this.user = new UserInfoDto();
        this.user.id = comment.userId;
        this.title = comment.title;
        this.content = comment.content;
        this.createTime = comment.createTime;
    }

}
