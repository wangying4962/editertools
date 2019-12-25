package com.study.editertools;

import lombok.Data;

@Data
public class TaskDO {
    private Integer id;
    private String fileName;
    private String address;
    private Integer status;
    private String result_mongo_id;
}
