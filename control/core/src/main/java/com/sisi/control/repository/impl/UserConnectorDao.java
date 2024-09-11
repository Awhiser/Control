package com.sisi.control.repository.impl;

import com.sisi.control.model.user.UserConnector;
import com.sisi.control.model.user.UserConnectorDto;
import com.sisi.control.repository.UserConnectorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserConnectorDao extends AbstractDao<UserConnector, UserConnectorRepository> {

    public UserConnectorDao(UserConnectorRepository userConnectorRepository) {
        super(userConnectorRepository);
    }

    public UserConnectorDto save(UserConnector connector){
        var res =saveDB(connector);
        return new UserConnectorDto(res);
    }

    public List<UserConnectorDto> getAll(){
        return findAll().stream().map(i -> new UserConnectorDto(i)).toList();
    }
    public UserConnectorDto getById(String id){
        var res =findById(id);
        return new UserConnectorDto(res);
    }



}
