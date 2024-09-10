package com.sisi.control.service.task;


import com.sisi.control.model.tasklink.TaskLinkType;
import com.sisi.control.model.tasklink.TaskLinkTypeDto;
import com.sisi.control.repository.impl.TaskLinkTypeDao;
import com.sisi.control.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskLinkTypeService {

    private TaskLinkTypeDao taskLinkTypeDao;

    @Autowired
    public TaskLinkTypeService(TaskLinkTypeDao dao){
        this.taskLinkTypeDao = dao;
    }

    public TaskLinkTypeDto create(TaskLinkType type){
        type.setId(CommonUtils.idGenerate());
        return taskLinkTypeDao.save(type);

    }

    public TaskLinkTypeDto update(TaskLinkType type){
        return taskLinkTypeDao.save(type);
    }

    public void delete(String id) {
        taskLinkTypeDao.deleteById(id);
    }

    public List<TaskLinkTypeDto> getAll(){

        return taskLinkTypeDao.getAll();
    }

    public TaskLinkTypeDto get(String id){
        return taskLinkTypeDao.get(id);
    }

    public List<TaskLinkTypeDto> getByIds(List<String> ids) {
        return taskLinkTypeDao.getByIds(ids);
    }

}
