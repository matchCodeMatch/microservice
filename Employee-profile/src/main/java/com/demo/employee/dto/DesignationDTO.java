package com.demo.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DesignationDTO {
    private String designationId;
    private String designationName;
    private String departmentName;
}