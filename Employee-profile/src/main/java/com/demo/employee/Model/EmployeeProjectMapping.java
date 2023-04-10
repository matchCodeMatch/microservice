package com.demo.employee.Model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class EmployeeProjectMapping {
    @Id
    private String employeeProjectMappingId;
    private String employeeId;
    private String projectId;

}
