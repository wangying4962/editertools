package com.study.editertools.dao;

import com.study.editertools.entity.TaskDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TaskDao {
    void addTask(TaskDO taskDO);

    List<TaskDO> listTask();

    TaskDO findById(@Param("id") Integer id);
}
