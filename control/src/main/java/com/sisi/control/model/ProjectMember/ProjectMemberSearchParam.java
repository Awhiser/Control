package com.sisi.control.model.ProjectMember;

import com.sisi.control.model.AbstractSearch;
import lombok.Data;

@Data
public class ProjectMemberSearchParam extends AbstractSearch {
    //默认项目中人数量小于人
    public String projectId;
    public String name;
    //List<user>=> userId

}
