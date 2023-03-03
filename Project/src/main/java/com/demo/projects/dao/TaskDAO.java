package com.demo.projects.dao;

import com.demo.projects.repository.TaskRepository;
import com.demo.projects.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskDAO implements DAOInterface<Task> {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllEntity() {
        return taskRepository.findAll();
    }

    @Override
    public Task getById(String id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public Task addEntity(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteAllEntity() {
        taskRepository.deleteAll();
    }

    @Override
    public void deleteById(String id) {
        taskRepository.deleteById(id);
    }
//    public boolean checkProjectId(String id){
//        return taskRepository.existsById(id);
//    }
    public Optional<String> checkProjectId(String id){
        return taskRepository.existByProjectID(id);
    }
}