package com.demo.projects.dto;

import com.demo.projects.entity.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDTO {
    private String projectId;
    private String projectTitle;
    private Status projectStatus;
    private LocalDate projectStartDate;
    private LocalDate projectDeadLine;
    private LocalDate projectEndDate;
}
