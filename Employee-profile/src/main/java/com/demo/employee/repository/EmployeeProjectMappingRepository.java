package com.demo.employee.repository;

import com.demo.employee.Model.EmployeeProjectMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface EmployeeProjectMappingRepository extends JpaRepository<EmployeeProjectMapping, String> {
    @Query(
            value = """
                    SELECT employee_id
                    FROM employee_project_mapping
                    WHERE employee_id = :employee_id
                    LIMIT 1;""",
            nativeQuery = true
    )
    public Optional<String> existByEmployeeID(@Param("employee_id") String employeeID);

    public List<EmployeeProjectMapping> findByEmployeeId(String employeeId);
    public List<EmployeeProjectMapping> findByProjectId(String projectId);

}
