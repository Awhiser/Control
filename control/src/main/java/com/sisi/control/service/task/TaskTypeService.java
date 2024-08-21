package com.sisi.control.service.task;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.tasktype.TaskType;
import com.sisi.control.repository.impl.TaskTypeDao;
import com.sisi.control.utils.CommonUtils;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
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

    public TaskType create(TaskType type){
        var context = ContextHolder.getContext();
        type.setIsDelete(false);
        type.setTenantId(context.getTenantId());
        type.setId(CommonUtils.idGenerate());
        var res =  taskTypeDao.save(type);
        return res;
    }

    public TaskType update(TaskType type){
        return taskTypeDao.save(type);
    }

    public void delete(String id){
        taskTypeDao.deleteById(id);
    }

    public List<TaskType> getList(){
        return taskTypeDao.findAll();
    }

}
