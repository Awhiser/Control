package com.sisi.control.model.project;

import com.sisi.control.model.user.UserVo;
import lombok.Data;

@Data
public class ProjectVo {
    String id;
    String name;
    UserVo leader;


    public ProjectVo() {

    }

    public ProjectVo(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.leader = new UserVo();
        this.leader.setId(project.getLeaderId());
    }

}
