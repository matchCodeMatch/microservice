package com.demo.department.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.UUID;

@Data
public class DepartmentDTO {
    private String departmentId;
    private String departmentName;
}
