package com.demo.employee.client;

import com.demo.employee.dto.ProjectDTO;
import com.demo.employee.dto.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@FeignClient(name = "PROJECT-MICROSERVICE",configuration = ServiceClientConfiguration.class)
public interface ProjectServiceClient {
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks();
    @GetMapping("projects/{id}")
    public ResponseEntity<ProjectDTO> getByProjectId(@PathVariable String id);

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDTO>> getAllProjects();
}
