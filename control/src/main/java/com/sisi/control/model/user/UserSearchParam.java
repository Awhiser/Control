package com.sisi.control.model.user;

import com.sisi.control.model.AbstractSearch;
import lombok.Data;

@Data
public class UserSearchParam extends AbstractSearch {

    public String id;
    public String name;
    public String displayName;



}
