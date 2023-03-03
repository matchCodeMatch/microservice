package com.demo.department.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
//@NoArgsConstructor
public class Designation {
    @Id
    private String designationId;
    private String designationName;
    private String departmentId;
}
