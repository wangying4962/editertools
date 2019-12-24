package com.study.editertools.service;

import com.study.editertools.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public void addStudent(){
        studentDao.addStudent("b",3,3);
    }
}
