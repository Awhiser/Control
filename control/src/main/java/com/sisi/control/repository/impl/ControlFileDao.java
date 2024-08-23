package com.sisi.control.repository.impl;

import com.sisi.control.model.controlfile.ControlFile;
import com.sisi.control.model.controlfile.ControlFileDto;
import com.sisi.control.repository.ControlFileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ControlFileDao extends AbstractDao<ControlFile, ControlFileRepository>{
    public ControlFileDao(ControlFileRepository controlFileRepository) {
        super(controlFileRepository);
    }

    public ControlFileDto save(ControlFile file){
        var res= saveDB(file);
        return new ControlFileDto(res);
    }

}
