package com.demo.employee.controller;

import com.demo.employee.Model.EmployeeProjectMapping;
import com.demo.employee.Service.EmployeeProjectMappingService;
import com.demo.employee.dto.EmployeeDTO;
import com.demo.employee.dto.EmployeeProjectMappingDTO;
import com.demo.employee.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/employees")
public class EmployeeProjectController {
    @Autowired
    private EmployeeProjectMappingService employeeProjectMappingService;

    @GetMapping("/projects")
    public ResponseEntity<List<EmployeeProjectMappingDTO>> getAll(@RequestHeader int offset, @RequestHeader int limit){
        return new ResponseEntity<>(employeeProjectMappingService.getAllEntity(offset,limit), HttpStatus.OK);
    }

    @PostMapping("/projects")
    public ResponseEntity<EmployeeProjectMappingDTO> addEntry(@RequestBody EmployeeProjectMapping employeeProjectMapping){
        return new ResponseEntity<>(employeeProjectMappingService.addEntity(employeeProjectMapping),HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/projects")
    public ResponseEntity<Map<String,String>> getAllProjectsForEmployeeId(@PathVariable String employeeId){
        return new ResponseEntity<>(employeeProjectMappingService.getAllProjectByEmployeeId(employeeId),HttpStatus.OK);
    }
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<Map<String,String>> getAllEmployeesByProjectId(@PathVariable String projectId){
        return new ResponseEntity<>(employeeProjectMappingService.getAllEmployeeByProjectId(projectId),HttpStatus.OK);
    }
    @DeleteMapping("/projects/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable String id){
        employeeProjectMappingService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/employee/{id}")
//    public ResponseEntity<List<ProjectDTO>> byEmployeeId(@PathVariable String employeeId){
//        return new ResponseEntity<>(employeeProjectMappingService.getAllProjectForId(employeeId),HttpStatus.OK);
//    }
//    @GetMapping("/project/{id}")
//    public ResponseEntity<List<EmployeeDTO>> byProjectId(@PathVariable String projectId){
//        return new ResponseEntity<>(employeeProjectMappingService.getAllEmployeeForId(projectId),HttpStatus.OK);
//    }

}
