package com.demo.projects.repository;

import com.demo.projects.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task,String> {

    @Query(
            value = "SELECT d.task_name FROM task d WHERE d.emp_name = :empName",
            nativeQuery = true)
    List<String> findByEmpName( @Param("empName") String EmpName);
    @Query(
            value = """
                    SELECT task_id
                    FROM task
                    WHERE project_id = :project_id
                    LIMIT 1;""",
            nativeQuery = true
    )
    public Optional<String> existByProjectID(@Param("project_id") String projectID);
}