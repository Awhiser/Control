package com.sisi.control.model.projectmember;

import com.sisi.control.model.user.UserVo;
import lombok.Data;

@Data
public class ProjectMemberVo {
    public String id;
    public String projectId;
    public UserVo user;
}
