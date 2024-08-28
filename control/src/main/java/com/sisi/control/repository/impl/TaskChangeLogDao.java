package com.sisi.control.repository.impl;

import com.sisi.control.model.taskchangelog.TaskChangeLog;
import com.sisi.control.model.taskchangelog.TaskChangeLogDto;
import com.sisi.control.repository.TaskChangeLogRepository;
import com.sisi.control.utils.jpatool.JPACondition;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class TaskChangeLogDao extends AbstractDao<TaskChangeLog, TaskChangeLogRepository>{
    public TaskChangeLogDao(TaskChangeLogRepository taskChangeLogRepository) {
        super(taskChangeLogRepository);
    }

    public List<TaskChangeLogDto> getByTaskId(String taskId) {
        Specification<TaskChangeLog> sp = JPACondition.<TaskChangeLog>builder()
                .eq(TaskChangeLog::getTaskId, taskId)
                .sortDesc(TaskChangeLog::getOperateTime)
                .build();
        var list = findBySpecification(sp);
        return list.stream().map(i->new TaskChangeLogDto(i)).toList();
    }


    public void saveAll(List<TaskChangeLog> list) {
        saveDBAll(list);
    }

}
