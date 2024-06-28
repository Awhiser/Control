package com.sisi.control.model;

public class AbstractSearch {
    public int pageSize = 50;
    public int pageIndex = 0;

    public int getPageSize(){
        if(this.pageSize > 500){
            return 500;
        }
        return this.pageSize;
    }

    public int getPageIndex(){
        if(this.pageIndex <= 0){
            return 0;
        }
        return  this.pageIndex;
    }

}
