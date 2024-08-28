package com.sisi.control.model;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageResult<T> {
    int totalPages;
    long totalElement;
    List<T> dataList;

    public PageResult(){

    }

    public PageResult(Page page){
        this.totalPages = page.getTotalPages();
        this.totalElement = page.getTotalElements();
    }

}