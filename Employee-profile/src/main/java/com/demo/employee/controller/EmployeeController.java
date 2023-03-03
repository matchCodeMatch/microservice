package com.demo.employee.controller;

import com.demo.employee.client.ProjectServiceClient;
import com.demo.employee.client.DepartmentServiceClient;
import com.demo.employee.dto.EmployeeDTO;
import com.demo.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.employee.Model.Employee;
import com.demo.employee.Service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService ;
    @Autowired
    DepartmentServiceClient feignInterfaceUtil;
    @Autowired
    private ProjectServiceClient projectServiceClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    //----------------------------------Showing All Employee details.-------------------------------
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        return new ResponseEntity<>(employeeService.getAllEntity(), HttpStatus.OK);
    }
    //----------------------------------Showing the employee with ID--------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable String id){
        return new ResponseEntity<>(employeeService.getById(id),HttpStatus.OK);
    }
//    @PostMapping("/id")
//    public ResponseEntity<List<EmployeeDTO>> getAllEmployeeByIds(@RequestBody List<String> idList){
//        return new ResponseEntity<>(employeeService.getAllEntityForId(idList),HttpStatus.OK);
//    }

    //------------------------------------To add an employee-----------------------------------
    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.addEntity(employee),HttpStatus.CREATED);
    }
    //-------------------------------To update the Employee detail-------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody Employee employee,@PathVariable String id){
        return new ResponseEntity<>(employeeService.updateEntity(id,employee,true),HttpStatus.OK);
    }
    //-----------------------------------To patch the employee-----------------------------------
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDTO> patchEmployee(@PathVariable String id,@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.updateEntity(id,employee,false),HttpStatus.OK);
    }
    //---------------------------------To delete an employee by ID------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable String id){
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    @DeleteMapping
//    public ResponseEntity<HttpStatus> deleteAllEmployee(){
//        employeeService.deleteAllEntity();
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

}

