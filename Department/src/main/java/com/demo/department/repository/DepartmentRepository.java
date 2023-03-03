package com.demo.department.repository;

import com.demo.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    Department findBydepartmentName(String deptName);

}
