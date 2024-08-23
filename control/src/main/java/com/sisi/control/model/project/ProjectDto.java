package com.sisi.control.model.project;

import com.sisi.control.model.user.UserInfoDto;
import lombok.Data;

@Data
public class ProjectDto {
    String id;
    String name;
    UserInfoDto leader;


    public ProjectDto() {

    }

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.leader = new UserInfoDto();
        this.leader.setId(project.getLeaderId());
    }

}
