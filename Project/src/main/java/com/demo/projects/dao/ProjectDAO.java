package com.demo.projects.dao;

import com.demo.projects.entity.Project;
import com.demo.projects.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Component
public class ProjectDAO implements DAOInterface<Project> {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAllEntity() {
        return projectRepository.findAll();
    }

    @Override
    public Project getById(String id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public Project addEntity(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteAllEntity() {
        projectRepository.deleteAll();
    }

    @Override
    public void deleteById(String id) {
        projectRepository.deleteById(id);
    }
}
