package com.demo.projects.controller;

import com.demo.projects.entity.Project;
import com.demo.projects.dto.ProjectDTO;
import com.demo.projects.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController  {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return new ResponseEntity<>(projectService.getAllEntity(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getByProjectId(@PathVariable String id) {
        return new ResponseEntity<>(projectService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> addEntity(@RequestBody Project project) {
        return new ResponseEntity<>(projectService.addEntity(project), HttpStatus.CREATED);

    }
//    @PostMapping("/id")
//    public ResponseEntity<List<ProjectDTO>> getALlName(@RequestBody List<String> idList){
//        return new ResponseEntity<>(projectService.getAllEntityByIds(idList),HttpStatus.OK);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody Project project,@PathVariable String id){
        return new ResponseEntity<>(projectService.updateProject(project,id),HttpStatus.OK);
    }
    @DeleteMapping
    public void deleteAllEntity() {
        projectService.deleteAllEntity();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        projectService.deleteById(id);
    }
}

