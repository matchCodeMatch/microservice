package com.demo.department.controller;

import com.demo.department.dto.DepartmentDTO;
import com.demo.department.model.Department;
import com.demo.department.model.Designation;
import com.demo.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> allDepartment(@RequestHeader int offset,@RequestHeader int limit){
        return new ResponseEntity<>(departmentService.getAllEntity(offset, limit), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody Department department){
        return new ResponseEntity<>(departmentService.addEntity(department),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable String id){
        return new ResponseEntity<>(departmentService.getById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody Department department,@PathVariable String id){
        return new ResponseEntity<>(departmentService.updateDepartment(department,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDepartmentById(@PathVariable String id){
        departmentService.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    @PostMapping("/all")
//    public void add(@RequestBody List<Designation> dep){
//        departmentService.addAll(dep);
//    }
//    Admin
//    @DeleteMapping()
//    public ResponseEntity deleteAllDepartment(){
//        departmentService.deleteAllEntity();
//        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

}
