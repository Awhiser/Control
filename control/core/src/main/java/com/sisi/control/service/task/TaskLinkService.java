package com.sisi.control.service.task;

import com.sisi.control.model.tasklink.TaskLink;
import com.sisi.control.model.tasklink.TaskLinkDto;
import com.sisi.control.repository.impl.TaskLinkDao;
import com.sisi.control.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskLinkService {

    private TaskLinkDao taskLinkDao;
    private TaskService taskService;
    private TaskLinkTypeService taskLinkTypeService;
    @Autowired
    public TaskLinkService(TaskLinkDao taskLinkDao,TaskService taskService, TaskLinkTypeService taskLinkTypeService ) {
        this.taskLinkDao = taskLinkDao;
        this.taskService = taskService;
        this.taskLinkTypeService = taskLinkTypeService;
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
       List<TaskLinkDto> links = taskLinkDao.getByTaskId(taskId);

       List<String> linkTypeIds = new ArrayList<>();
       List<String> taskIds = new ArrayList<>();
        //获取所需的TaskLink数据
       links.forEach(i -> {
           linkTypeIds.add(i.getLink().getId());
           taskIds.add(i.getOutTask().getId());
           taskIds.add(i.getInTask().getId());
       });
       var linkMap =  taskLinkTypeService.getByIds(linkTypeIds).stream().collect(Collectors.toMap(k->k.id,v->v));
       var taskMap = taskService.getByIds(taskIds).stream().collect(Collectors.toMap(k->k.id,v->v));
       links.forEach(i -> {
            //填充link信息
            if(linkMap.containsKey(i.getLink().getId())){
                i.setLink(linkMap.get(i.getLink().getId()));
            }
            //填充task信息
            if(taskMap.containsKey(i.getOutTask().getId())) {
                i.setOutTask(taskMap.get(i.getOutTask().getId()));
            }
            if(taskMap.containsKey(i.getInTask().getId())) {
                i.setInTask(taskMap.get(i.getInTask().getId()));
            }
       });

       return links;
    }



}
