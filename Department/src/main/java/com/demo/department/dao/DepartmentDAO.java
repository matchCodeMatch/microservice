package com.demo.department.dao;

import com.demo.department.model.Department;
import com.demo.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DepartmentDAO implements DAOInterface<Department>{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public List<Department> getAllEntity(int offset, int limit) {
        return departmentRepository.findAll(PageRequest.of(offset, limit)).stream().toList();
    }

    @Override
    public Department getById(String id) {return departmentRepository.findById(id).get();}

    @Override
    public Department addEntity(Department department) {
        return departmentRepository.saveAndFlush(department);
    }

    @Override
    public void deleteAllEntity() {
        departmentRepository.deleteAll();
    }

    @Override
    public void deleteById(String id) {
        departmentRepository.deleteById(id);
    }
}
