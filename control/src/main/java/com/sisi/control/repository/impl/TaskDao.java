package com.sisi.control.repository.impl;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.PageResult;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.task.TaskDto;
import com.sisi.control.model.task.TaskSearchParam;
import com.sisi.control.repository.TaskRepository;
import com.sisi.control.utils.jpatool.JPACondition;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

@Repository
public class TaskDao extends AbstractDao<Task, TaskRepository>{
    public TaskDao(TaskRepository taskRepository) {
        super(taskRepository);
    }


    public PageResult<TaskDto> getTaskPage(TaskSearchParam param) {
//, COUNT(*) OVER () AS totalCount
        String selectSql = "Select * ";
        String countSql = "Select Count(*) ";

        StringBuffer sql = new StringBuffer("from task where tenantid = '" + ContextHolder.getContext().getTenantId()+"' " + " And isdelete = false");


        if (StringUtils.hasText(param.getProjectId())) {
            sql.append(" And projectId = '"+ param.getProjectId()+"'");
        }
        if (StringUtils.hasText(param.getTitle())) {
            sql.append(" And title like "+ "'%" + param.getTitle() + "%'");

        }

        if (!CollectionUtils.isEmpty(param.getAssignee())) {
            sql.append(" And assignee in  "+ "(" + sqlStringArray(param.getAssignee())  + ")");

        }
        if (!CollectionUtils.isEmpty(param.getPriority())) {
            sql.append(" And priority in  "+ "(" + sqlStringArray(param.getPriority()) + ")");

        }
        if (!CollectionUtils.isEmpty(param.getStatus())) {
            sql.append(" And status in  "+ "(" + sqlStringArray(param.getStatus())  + ")");

        }
        if (!CollectionUtils.isEmpty(param.getType())) {
            sql.append(" And type in  "+ "(" + sqlStringArray(param.getType())  + ")");

        }

        if (!CollectionUtils.isEmpty(param.getTags())) {
            sql.append(" And STRING_TO_ARRAY( tags, ',' ) &&  "+ "ARRAY[" + sqlStringArray(param.getTags()) + "]");
        }

        var count = executeCountSql(countSql + sql.toString());

        sql.append(" ORDER BY createtime DESC  LIMIT "+ param.getPageSize()   +" OFFSET "+ param.getPageIndex() * param.getPageSize());
        var res =  executeSelectSql(selectSql + sql.toString(),Task.class);


        var resData = res.stream().map(i -> new TaskDto(i)).toList();
        PageResult<TaskDto> pageRes = new PageResult<>();
        pageRes.setTotalElement(count);
        pageRes.setDataList(resData);

        return pageRes;
    }

    public TaskDto getById(String id){
        var task = findById(id);
        if(task == null) {
            return new TaskDto();
        }
        TaskDto bo = new TaskDto(task);
        return bo;
    }

    public List<TaskDto> getByIds(List<String> ids){
        var tasks = findByIds(ids);
        return tasks.stream().map(i-> new TaskDto(i)).toList();
    }

    public TaskDto save(Task task){
        var res =  saveDB(task);
        return new TaskDto(res);
    }

    @Transactional
    public void updateStatus(String taskId, String status) {
        String sql  = "UPDATE task SET status = '"+status+"' WHERE id = '"+taskId+"'";
        executeUpdateSql(sql);
    }

    public String sqlStringArray(List list){
        String sqlStr = "";
        for (int i = 0; i < list.size() ; i++) {
            sqlStr += "'"+ list.get(i)+"'";
            if ( i != list.size() -1){
                sqlStr += ",";
            }
        }
        return  sqlStr ;
    }


}
