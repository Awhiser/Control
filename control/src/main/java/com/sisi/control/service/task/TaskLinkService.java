package com.sisi.control.service.task;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.tasklink.TaskLink;
import com.sisi.control.model.tasklink.TaskLinkDto;
import com.sisi.control.repository.impl.TaskLinkDao;
import com.sisi.control.utils.CommonUtils;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLinkService {

    private TaskLinkDao taskLinkDao;

    @Autowired
    public TaskLinkService(TaskLinkDao taskLinkDao) {
        this.taskLinkDao = taskLinkDao;
    }

    public TaskLinkDto create(TaskLink link){
       link.setId(CommonUtils.idGenerate());
       var res = taskLinkDao.save(link);
       return res;
    }

    public TaskLinkDto update(TaskLink link){
        return taskLinkDao.save(link);
    }

    public TaskLinkDto getById(String id){
        return taskLinkDao.getById(id);
    }

    public void deleteById(String id){
        taskLinkDao.deleteByIdWithRealRemove(id);
    }

    public List<TaskLinkDto> getByTaskId(String taskId){
       return taskLinkDao.getByTaskId(taskId);
    }



}
