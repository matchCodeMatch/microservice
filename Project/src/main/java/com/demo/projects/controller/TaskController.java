package com.demo.projects.controller;

import com.demo.projects.entity.Task;
import com.demo.projects.dto.TaskDTO;
import com.demo.projects.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllEntity(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getById(@PathVariable String id) {
        return new ResponseEntity<>(taskService.getById(id), HttpStatus.OK);
    }
//    @PostMapping("/id")
//    public ResponseEntity<List<TaskDTO>> getNameById(@RequestBody List<String> idList) {
//        return new ResponseEntity<>(taskService.getAllEntityByIds(idList),HttpStatus.OK);
//    }
    @PostMapping
    public ResponseEntity<TaskDTO> addEntity(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.addEntity(task),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody Task task,@PathVariable String id){
        return new ResponseEntity<>(taskService.updateTask(task,id),HttpStatus.OK);
    }
    @DeleteMapping
    public void deleteAllEntity() {
        taskService.deleteAllEntity();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        taskService.deleteById(id);
    }
}
