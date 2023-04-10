package com.demo.employee.dao;

import com.demo.employee.Model.Employee;
import com.demo.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDAO implements DAOInterface<Employee>{
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEntity(int offset,int limit) {
        return employeeRepository.findAll(PageRequest.of(offset, limit)).stream().toList();
    }

    @Override
    public Employee getById(String id) {
        return employeeRepository.getById(id);
    }
    @Override
    public Employee addEntity(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteAllEntity() {
        employeeRepository.deleteAll();
    }

    @Override
    public void deleteById(String id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getAllByDesignationId(String designationId){
        return employeeRepository.findByDesignationId(designationId);
    }
}
