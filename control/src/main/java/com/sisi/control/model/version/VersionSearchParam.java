package com.sisi.control.model.version;

import com.sisi.control.model.AbstractSearch;
import lombok.Data;

@Data
public class VersionSearchParam extends AbstractSearch {
    public String name;
    public String projectId;
}
