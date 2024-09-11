package com.sisi.control.service.task;

import com.sisi.control.model.tasktype.TaskType;
import com.sisi.control.model.tasktype.TaskTypeDto;
import com.sisi.control.repository.impl.TaskTypeDao;
import com.sisi.control.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTypeService {
    private TaskTypeDao taskTypeDao;

    @Autowired
    TaskTypeService(TaskTypeDao taskTypeDao){
        this.taskTypeDao = taskTypeDao;
    }

    public TaskTypeDto create(TaskType type){
        type.setId(CommonUtils.idGenerate());
        var res =  taskTypeDao.save(type);
        return res;
    }

    public TaskTypeDto update(TaskType type){
        return  taskTypeDao.save(type);
    }

    public void delete(String id){
        taskTypeDao.deleteById(id);
    }

    public List<TaskTypeDto> getList(){
        return taskTypeDao.getAll();
    }

}
