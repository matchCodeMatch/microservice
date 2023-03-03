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

@RestController()
@RequestMapping("/employees/projects")
public class EmployeeProjectController {
    @Autowired
    private EmployeeProjectMappingService employeeProjectMappingService;

    @GetMapping
    public ResponseEntity<List<EmployeeProjectMappingDTO>> getAll(){
        return new ResponseEntity<>(employeeProjectMappingService.getAllEntity(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeProjectMappingDTO> addEntry(@RequestBody EmployeeProjectMapping employeeProjectMapping){
        return new ResponseEntity<>(employeeProjectMappingService.addEntity(employeeProjectMapping),HttpStatus.CREATED);
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
