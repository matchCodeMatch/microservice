package com.demo.employee.Model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    public enum Gender{
        MALE, FEMALE
    }
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    private String email;
    private LocalDate dob;
    private LocalDate hireDate;
    private Double salary;
    private Integer phNumber;
    private String designationId;
}