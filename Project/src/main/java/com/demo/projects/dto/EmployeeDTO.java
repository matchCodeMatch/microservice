package com.demo.projects.dto;
import lombok.*;
import java.time.LocalDate;

@Data
public class EmployeeDTO {
    public enum Gender {
        MALE, FEMALE
    }
    private String employeeId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private LocalDate dob;
    private LocalDate hireDate;
    private Double salary;
    private Integer phNumber;
    private String designationName;
}
