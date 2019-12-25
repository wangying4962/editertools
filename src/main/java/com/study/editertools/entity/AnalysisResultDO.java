package com.study.editertools.entity;

import lombok.Data;

@Data
public class AnalysisResultDO {
    private Integer id;
    private Integer taskId;
    private Integer page;
    private Integer line;
    private String keyWord;
    private String description;
    private Integer status;
}
