package com.demo.department.repository;

import com.demo.department.model.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, String> {

    @Query(
            value = "SELECT d.designation_name FROM designation d WHERE d.dept_id = :deptName",
            nativeQuery = true)
    List<String> findBydepartmentName( @Param("deptName") Long deptName);

    @Query(
            value = "SELECT d.designation_id FROM designation d WHERE d.dept_id = :deptName",
            nativeQuery = true)
    List<String> findDesignation( @Param("deptName") Long deptName);
    @Query(
            value = "SELECT designation_id FROM designation WHERE department_id = :department_id LIMIT 1;",
            nativeQuery = true
    )
    public Optional<String> existByDepartmentID(@Param("department_id") String departmentId);

    public List<Designation> findByDepartmentId(String departmentId);

}
