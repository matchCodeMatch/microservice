package com.demo.projects.service;

import com.demo.projects.advice.EntityMappedException;
import com.demo.projects.dao.ProjectDAO;
import com.demo.projects.dao.TaskDAO;
import com.demo.projects.entity.Project;
import com.demo.projects.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService implements Services<ProjectDTO> {

    @Autowired
    private ProjectDAO projectDAO;
    @Autowired
    private TaskDAO taskDAO;

    @Override
    public List<ProjectDTO> getAllEntity() {
        return convertProjectListToProjectDTOList(projectDAO.getAllEntity());
    }

    @Override
    public ProjectDTO getById(String id) {
        UUID.fromString(id);
        return convertProjectToProjectDTO(projectDAO.getById(id));
    }

    public ProjectDTO addEntity(Project project) {
        project.setProjectTitle(project.getProjectTitle().trim());
        if(project.getProjectTitle() == null ||  project.getProjectTitle().length() < 3 || project.getProjectStatus() == null)
            throw new NullPointerException();
        project.setProjectId(String.valueOf(UUID.randomUUID()));
        return convertProjectToProjectDTO(projectDAO.addEntity(project));
    }
    public ProjectDTO updateProject(Project project, String id){
        UUID.fromString(id);
        if(project.getProjectTitle() == null||
           project.getProjectStatus() == null)
                throw new NullPointerException();

        Project oldProject = projectDAO.getById(id);
        oldProject.setProjectTitle(project.getProjectTitle());
        oldProject.setProjectStatus(project.getProjectStatus());
        oldProject.setProjectStartDate(project.getProjectStartDate());
        oldProject.setProjectEndDate(project.getProjectEndDate());
        oldProject.setProjectDeadLine(project.getProjectDeadLine());
        return convertProjectToProjectDTO(projectDAO.addEntity(oldProject));
    }
    @Override
    public void deleteAllEntity() {
        projectDAO.deleteAllEntity();
    }

    @Override
    public void deleteById(String id) {
        UUID.fromString(id);
        if(taskDAO.checkProjectId(id).isEmpty())
            projectDAO.deleteById(id);
        else
            throw new EntityMappedException();
    }

    public List<ProjectDTO> convertProjectListToProjectDTOList(List<Project> projects) {
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        projects.forEach(project -> projectDTOList.add(convertProjectToProjectDTO(project)));
        return projectDTOList;
    }

    public ProjectDTO convertProjectToProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(project.getProjectId());
        projectDTO.setProjectTitle(project.getProjectTitle());
        projectDTO.setProjectStatus(project.getProjectStatus());
        projectDTO.setProjectStartDate(project.getProjectStartDate());
        projectDTO.setProjectEndDate(project.getProjectEndDate());
        projectDTO.setProjectDeadLine(project.getProjectDeadLine());
        return projectDTO;
    }
}
