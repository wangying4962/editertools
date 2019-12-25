package com.study.editertools.entity;

import lombok.Data;

@Data
public class TaskDO {
    private Integer id;
    private String fileName;
    private String address;
    private Integer status;
    private String resultMongoId;
}
