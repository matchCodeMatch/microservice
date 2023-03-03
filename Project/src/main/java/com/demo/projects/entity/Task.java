package com.demo.projects.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Task {
    @Id
    private String taskId;
    private String taskName;
    private String projectId;
    private String employeeId;
    private Status taskStatus;
}
