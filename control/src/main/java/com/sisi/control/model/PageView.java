package com.sisi.control.model;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageView<T> {
    int totalPages;
    long totalElement;
    List<T> dataList;

    public PageView(){

    }

    public PageView(Page page){
        this.totalPages = page.getTotalPages();
        this.totalElement = page.getTotalElements();
    }

}
