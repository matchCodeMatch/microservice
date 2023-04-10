package com.demo.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    public enum Status{
        NOT_STARTED, IN_PROGRESS, COMPLETED, CANCELLED
    }
    private String taskId;
    private String taskName;
    private String projectName;
    private String employeeName;
    private Status taskStatus;
}
