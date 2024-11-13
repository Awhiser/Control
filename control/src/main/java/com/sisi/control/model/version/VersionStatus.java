package com.sisi.control.model.version;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class VersionStatus extends AbstractEntity {
    public String name;
    //能否在创建单子时显示
    public boolean isShow;


    public VersionStatusDto toDto(){
        VersionStatusDto dto = new VersionStatusDto();
        dto.setId(id);
        dto.setName(name);
        dto.setShow(isShow);
        return dto;
    }

}
