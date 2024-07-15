package com.sisi.control.model.project;

import com.sisi.control.model.AbstractSearch;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Data
public class ProjectSearchParam extends AbstractSearch {
    List<String> ids;
    String name;
    String currentUserId;
    //是否携带Leader详细信息
    boolean extLeader =false;




}
