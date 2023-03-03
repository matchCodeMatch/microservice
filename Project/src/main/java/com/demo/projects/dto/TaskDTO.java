package com.demo.projects.dto;

import com.demo.projects.entity.Status;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskDTO {
    private String taskId;
    private String taskName;
    private String projectName;
    private String employeeName;
    private Status taskStatus;
}
