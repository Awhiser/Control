package com.sisi.control.model.projectmember;

import com.sisi.control.model.user.UserInfoDto;
import lombok.Data;

@Data
public class ProjectMemberDto {
    public String id;
    public String projectId;
    public UserInfoDto user;

    public ProjectMemberDto() {

    }
    public ProjectMemberDto(ProjectMember member) {
        this.id = member.id;
        this.projectId = member.projectId;
        this.user = new UserInfoDto();
        this.user.id = member.userId;
    }
}
