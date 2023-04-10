package com.demo.employee.dao;

import com.demo.employee.Model.EmployeeProjectMapping;
import com.demo.employee.repository.EmployeeProjectMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class EmployeeProjectMappingDAO implements DAOInterface<EmployeeProjectMapping>{

    @Autowired
    private EmployeeProjectMappingRepository employeeProjectMappingRepository;
    @Override
    public List<EmployeeProjectMapping> getAllEntity(int offset, int limit) {
        return employeeProjectMappingRepository.findAll(PageRequest.of(offset, limit)).stream().toList();
    }

    @Override
    public EmployeeProjectMapping getById(String id) {
        return employeeProjectMappingRepository.findById(id).get();
    }

    @Override
    public EmployeeProjectMapping addEntity(EmployeeProjectMapping employeeProjectMapping) {
        return employeeProjectMappingRepository.save(employeeProjectMapping);
    }

    @Override
    public void deleteAllEntity() {
        employeeProjectMappingRepository.deleteAll();
    }

    @Override
    public void deleteById(String id) {
        employeeProjectMappingRepository.deleteById(id);
    }

    public Optional<String> checkByEmployeeId(String empId){
       return employeeProjectMappingRepository.existByEmployeeID(empId);
    }
    public List<EmployeeProjectMapping> getAllByProjectId(String projectId){
        return employeeProjectMappingRepository.findByProjectId(projectId);
    }
    public List<EmployeeProjectMapping> getAllByEmployeeId(String employeeId){
        return employeeProjectMappingRepository.findByEmployeeId(employeeId);
    }
}
