package com.study.editertools.service;

import com.study.editertools.dao.StudentDao;
import com.study.editertools.dao.TaskDao;
import com.study.editertools.entity.TaskDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskDao taskDao;

    public void addStudent(TaskDO taskDO) {
        taskDao.addTask(taskDO);
    }

    public List<TaskDO> listTask(){
        return taskDao.listTask();
    }

    public TaskDO findById(Integer id){
        return taskDao.findById(id);
    }
}
