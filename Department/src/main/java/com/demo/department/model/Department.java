package com.demo.department.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Department {
    @Id
    private String departmentId;
    @Column(unique = true)
    private String departmentName;
    public Department(String deptName) {
        this.departmentName = deptName;
    }
}