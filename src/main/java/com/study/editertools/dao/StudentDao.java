package com.study.editertools.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface StudentDao {
    void addStudent(@Param("name") String name, @Param("age") Integer age, @Param("classId") Integer classId);
}
