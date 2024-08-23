package com.sisi.control.model.user;

import lombok.Data;

@Data
public class UserConnectorDto {
    public String id;
    public String name;
    public String url;
    public String displayNameField;
    public String idField;
    public String nameField;
    public String mailField;
    public String passwordField;

    public UserConnectorDto(UserConnector connector) {
        this.id = connector.getId();
        this.name = connector.getName();
        this.url = connector.getUrl();
        this.displayNameField = connector.getDisplayNameField();
        this.idField = connector.getIdField();
        this.nameField = connector.getNameField();
        this.mailField = connector.getMailField();
        this.passwordField = connector.getPasswordField();
    }

}
