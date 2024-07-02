package com.sisi.control.repository.impl;

import com.sisi.control.model.user.UserConnector;
import com.sisi.control.repository.UserConnectorRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserConnectorDao extends AbstractDao<UserConnector, UserConnectorRepository> {

    public UserConnectorDao(UserConnectorRepository userConnectorRepository) {
        super(userConnectorRepository);
    }





}
