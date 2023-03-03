package com.demo.employee.dto;

import com.demo.employee.Model.Employee;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class EmployeeDTO {
    public enum Gender{
        MALE, FEMALE
    }
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private Employee.Gender gender;
    private String email;
    private LocalDate dob;
    private LocalDate hireDate;
    private Double salary;
    private Integer phNumber;
    private String designationName;
}