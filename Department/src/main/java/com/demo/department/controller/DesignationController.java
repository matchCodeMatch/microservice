package com.demo.department.controller;

import com.demo.department.dto.DesignationDTO;
import com.demo.department.service.DesignationService;
import com.demo.department.model.Designation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/designations")
public class DesignationController {

    @Autowired
    private DesignationService designationService;


    @GetMapping
    public ResponseEntity<List<DesignationDTO>> allDesignation(){
        return new ResponseEntity<>(designationService.getAllEntity(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DesignationDTO> addDesignation(@RequestBody Designation designation){
        return new ResponseEntity<>(designationService.addEntity(designation),HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DesignationDTO> getDesignationById (@PathVariable String id){
        return new ResponseEntity<>(designationService.getById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DesignationDTO> updateDesignation(@RequestBody Designation designation, @PathVariable String id){
        return new ResponseEntity<>(designationService.updateDesignation(designation,id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDepartmentById(@PathVariable String id){
        designationService.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    Admin
//    @DeleteMapping()
//    public ResponseEntity deleteAllDepartment(){
//        designationService.deleteAllEntity();
//        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
