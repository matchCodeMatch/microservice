package com.demo.projects.service;

import com.demo.projects.clients.EmployeeServiceClient;
import com.demo.projects.dao.ProjectDAO;
import com.demo.projects.dao.TaskDAO;
import com.demo.projects.dto.TaskDTO;
import com.demo.projects.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Service
public class TaskService implements Services<TaskDTO> {
    @Autowired
    private TaskDAO taskDAO;
    @Autowired
    private ProjectDAO projectDAO;
    @Autowired
    private EmployeeServiceClient employeeServiceClient;

    public List<TaskDTO> convertTaskListToTaskDTOList (List<Task> tasks){
        List<TaskDTO> taskDTOList = new ArrayList<>();
        tasks.forEach(task -> taskDTOList.add(convertTaskToTaskDTO(task)));
        return taskDTOList;
    }
    public TaskDTO convertTaskToTaskDTO (Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setTaskStatus(task.getTaskStatus());
        taskDTO.setTaskId(task.getTaskId());
        taskDTO.setProjectName(projectDAO.getById(task.getProjectId()).getProjectTitle());
        if (task.getEmployeeId() != null)
           taskDTO.setEmployeeName(employeeServiceClient.getEmployeeById(task.getEmployeeId()).getBody().getFirstName());
        return taskDTO;
    }

    @Override
    public List<TaskDTO> getAllEntity(int offset,int limit) {
        return convertTaskListToTaskDTOList(taskDAO.getAllEntity(offset,limit));
    }

    @Override
    public TaskDTO getById(String id) {
        UUID.fromString(id);
        return convertTaskToTaskDTO(taskDAO.getById(id));
    }

    public TaskDTO addEntity(Task task){
            validateTask(task);
            task.setTaskId(String.valueOf(UUID.randomUUID()));
            return convertTaskToTaskDTO(taskDAO.addEntity(task));

    }
    public List<TaskDTO> getAllTaskByProjectId(String id){
        return convertTaskListToTaskDTOList(taskDAO.getAllByProjectId(id));
    }
    public TaskDTO updateTask (Task task, String id){
        UUID.fromString(id);
        validateTask(task);
        Task oldTask = taskDAO.getById(id);
        oldTask.setTaskName(task.getTaskName());
        oldTask.setTaskStatus(task.getTaskStatus());
        oldTask.setProjectId(task.getProjectId());
        oldTask.setEmployeeId(task.getEmployeeId());
        return convertTaskToTaskDTO(taskDAO.addEntity(oldTask));
    }
    public void validateTask(Task task) {
        task.setTaskName(task.getTaskName().trim());
        if (task.getTaskName() == null ||
                task.getTaskName().length() < 3 ||
                task.getTaskStatus() == null ||
                task.getProjectId() == null
        )
            throw new NullPointerException();
        if (projectDAO.getById(task.getProjectId()) != null) {
            if (task.getEmployeeId() != null)
                if (employeeServiceClient.getEmployeeById(task.getEmployeeId()) == null)
                    throw new NoSuchElementException();
        }
    }
    @Override
    public void deleteAllEntity() {
        taskDAO.deleteAllEntity();
    }

    @Override
    public void deleteById(String id) {
        taskDAO.deleteById(id);
    }

    public List<TaskDTO> getAllTaskByEmployeeId(String id) {
        return convertTaskListToTaskDTOList(taskDAO.getAllByEmployeeId(id));
    }

    public void addAll(List<Task> tasks) {
        tasks.forEach(this::addEntity);
    }
}
