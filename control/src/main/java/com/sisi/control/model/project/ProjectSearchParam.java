package com.sisi.control.model.project;

import com.sisi.control.model.AbstractSearch;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public class ProjectSearchParam extends AbstractSearch {
    String id;
    String name;

    //是否携带Leader详细信息
    boolean extLeader =false;




}
