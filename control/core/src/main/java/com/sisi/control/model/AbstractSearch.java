package com.sisi.control.model;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Data
public class AbstractSearch {
    public List<String> ids;
    public boolean disablePage = false;
    public int pageSize = 50;
    public int pageIndex = 0;

    public int getPageSize(){
        if(disablePage){
            return Integer.MAX_VALUE;
        }
        if(this.pageSize > 500){
            return 500;
        }
        return this.pageSize;
    }

    public int getPageIndex(){
        if(disablePage){
            return 0;
        }
        if(this.pageIndex <= 0){
            return 0;
        }
        return  this.pageIndex;
    }

    public PageRequest getPageRequest(){
        return PageRequest.of(getPageIndex(), getPageSize());
    }

}
