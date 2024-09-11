package com.sisi.control.model.user;


import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
@Entity
@Table(name = "UserInfo",
        indexes = {
        @Index(columnList = "id",name = "id"),
        @Index(columnList = "name",name = "name"),
        @Index(columnList = "tenantId",name = "tenantId")
})
//@MappedSuperclass//实体继承映射
public class UserInfo extends AbstractEntity {
    @Id
    public String id;
    public String name;
    public String displayName;
    public String phone;
    public String mail;
    public String password;
    public String getDisplayName() {
        return StringUtils.hasText(displayName) ? displayName : name;
    }

    public UserInfoDto toDto(){
        UserInfoDto dto = new UserInfoDto();
        dto.id = getId();
        dto.name = getName();
        dto.displayName = getDisplayName();
        dto.phone = getPhone();
        dto.mail = getMail();
        return dto;
    }

}
