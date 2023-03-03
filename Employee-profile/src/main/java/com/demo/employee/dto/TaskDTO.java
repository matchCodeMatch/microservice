package com.demo.employee.dto;

import lombok.Data;

@Data
public class TaskDTO {
    private enum Status{
        NOT_STARTED, IN_PROGRESS, COMPLETED, CANCELLED
    }
    private String taskId;
    private String taskName;
    private String projectName;
    private String employeeName;
    private Status taskStatus;
}
