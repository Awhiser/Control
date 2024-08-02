package com.sisi.control.service.task;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.tasklink.TaskLink;
import com.sisi.control.repository.impl.TaskLinkDao;
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

    public TaskLink create(TaskLink link){
       link.setId(ContextHolder.getContext().tenantId+"-"+ NanoIdUtils.randomNanoId());
       var res = taskLinkDao.save(link);
       return res;
    }

    public TaskLink update(TaskLink link){
        return taskLinkDao.save(link);
    }

    public TaskLink getById(String id){
        return taskLinkDao.findById(id);
    }

    public List<TaskLink> getByIds(List<String> ids){
        return taskLinkDao.findByIds(ids);
    }

    public void deleteById(String id){
        taskLinkDao.deleteByIdWithRealRemove(id);
    }

    public List<TaskLink> getByTaskId(String taskId){
       return taskLinkDao.getByTaskId(taskId);
    }



}
