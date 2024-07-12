package com.sisi.control.model.user;

import com.sisi.control.model.AbstractSearch;
import lombok.Data;

import java.util.List;

@Data
public class UserSearchParam extends AbstractSearch {

    public List<String> ids;
    public String name;
    public String displayName;



}
