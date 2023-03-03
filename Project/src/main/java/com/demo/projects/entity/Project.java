package com.demo.projects.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document
@Data
public class Project {
    @Id
    private String projectId;
    private String projectTitle;
    private Status projectStatus;
    private LocalDate projectStartDate;
    private LocalDate projectDeadLine;
    private LocalDate projectEndDate;
}
