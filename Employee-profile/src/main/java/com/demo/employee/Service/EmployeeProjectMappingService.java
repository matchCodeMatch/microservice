package com.demo.employee.Service;


import com.demo.employee.Advice.EntityMappedException;
import com.demo.employee.Model.EmployeeProjectMapping;
import com.demo.employee.client.ProjectServiceClient;
import com.demo.employee.dao.EmployeeDAO;
import com.demo.employee.dao.EmployeeProjectMappingDAO;
import com.demo.employee.dto.EmployeeProjectMappingDTO;
import com.demo.employee.dto.ProjectDTO;
import com.demo.employee.repository.EmployeeProjectMappingRepository;
import com.demo.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeProjectMappingService implements Services<EmployeeProjectMappingDTO> {
    @Autowired
    private EmployeeProjectMappingDAO employeeProjectMappingDAO;
    @Autowired
    private ProjectServiceClient projectServiceClient;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeProjectMappingRepository employeeProjectMappingRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeProjectMappingDTO> getAllEntity(int offset, int limit) {
        ArrayList<EmployeeProjectMappingDTO> list = new ArrayList<>();
        employeeProjectMappingDAO
                .getAllEntity(offset, limit)
                .forEach(
                        employeeProjectMappingDAO->list
                                .add(convertEmpProjectToEmpProjectDTO(
                                        employeeProjectMappingDAO)
                                )
                );
        return list;

    }
    public EmployeeProjectMappingDTO addEntity(EmployeeProjectMapping employeeProjectMapping){
        if((employeeDAO.getById(employeeProjectMapping.getEmployeeId()) != null) &&
            (projectServiceClient.getByProjectId(employeeProjectMapping.getProjectId())) != null){
                employeeProjectMapping.setEmployeeProjectMappingId(String.valueOf(UUID.randomUUID()));
                return convertEmpProjectToEmpProjectDTO(employeeProjectMappingDAO.addEntity(employeeProjectMapping));}
        else
            throw new EntityMappedException();
    }

    @Override
    public EmployeeProjectMappingDTO getById(String id) {
        UUID.fromString(id);
        return convertEmpProjectToEmpProjectDTO(employeeProjectMappingDAO.getById(id));
    }
    public Map<String,String> getAllEmployeeByProjectId(String projectId){
        UUID.fromString(projectId);
        Map<String,String> employeeMap = new HashMap<>();
        employeeProjectMappingDAO.getAllByProjectId(projectId).forEach(employeeProjectMapping -> {
            employeeMap.put(employeeProjectMapping.getEmployeeId(),
                    employeeDAO.getById(employeeProjectMapping
                                    .getEmployeeId())
                            .getFirstName());
        });
        return employeeMap;
    }
    public Map<String,String> getAllProjectByEmployeeId(String employeeId){
        UUID.fromString(employeeId);
        Map<String,String> projectMap = new HashMap<>();
        employeeProjectMappingDAO.getAllByEmployeeId(employeeId).forEach(employeeProjectMapping -> {
            ProjectDTO project = projectServiceClient.getByProjectId(employeeProjectMapping.getProjectId()).getBody();
            projectMap.put(project.getProjectId(), project.getProjectTitle());
        });
        return projectMap;
    }

//    @Override
//    public void deleteAllEntity() {
//        employeeProjectMappingDAO.deleteAllEntity();
//    }

    @Override
    public void deleteById(String id) {
        UUID.fromString(id);
        employeeProjectMappingDAO.deleteById(id);
    }

    //    @Override
//    public void deleteById(String id) {
//        boolean flag = true;
//        List<ProjectDTO> projectDTOList;
//        projectDTOList = projectServiceClient.getAllProjects().getBody();
//        for(ProjectDTO projectDTO : projectDTOList){
//            if(projectDTO.getProjectId() == employeeProjectMappingDAO.getById(id).getProjectId()){
//                flag = false;
//                break;
//            }
//        }
//        if(!flag && employeeDAO.getById(employeeProjectMappingDAO.getById(id).getEmployeeId()) != null)
//            throw new EntityMappedException();
//        employeeProjectMappingDAO.deleteById(id);
//    }
    public EmployeeProjectMappingDTO convertEmpProjectToEmpProjectDTO(EmployeeProjectMapping employeeProjectMapping){
        EmployeeProjectMappingDTO employeeProjectMappingDTO = new EmployeeProjectMappingDTO();
        employeeProjectMappingDTO.setEmployeeProjectMappingId(employeeProjectMapping
                .getEmployeeProjectMappingId());
        employeeProjectMappingDTO.setProjectName(
                projectServiceClient
                        .getByProjectId(employeeProjectMapping.getProjectId())
                        .getBody().getProjectTitle()
        );
        employeeProjectMappingDTO.setEmpName(employeeDAO
                .getById(employeeProjectMapping.getEmployeeId()).getFirstName()
        );
        return employeeProjectMappingDTO;
    }
}
