package com.sisi.control.model.user;

import com.sisi.control.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class UserConnector extends AbstractEntity {
     public String name;
     public String url;
     public String displayNameField;
     public String idField;
     public String nameField;
     public String mailField;
     public String passwordField;

}
