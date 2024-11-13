package com.sisi.control.model.version;

import lombok.Data;

@Data
public class VersionStatusDto {
    public String id;
    public String name;
    //能否在创建单子时显示
    public boolean isShow;
}
