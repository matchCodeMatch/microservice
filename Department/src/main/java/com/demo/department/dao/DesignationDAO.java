package com.demo.department.dao;

import com.demo.department.repository.DesignationRepository;
import com.demo.department.model.Designation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DesignationDAO implements DAOInterface<Designation>{
    @Autowired
    private DesignationRepository designationRepository;
    @Override
    public List<Designation> getAllEntity(int offset, int limit) {
        return designationRepository.findAll(PageRequest.of(offset, limit)).stream().toList();
    }

    @Override
    public Designation getById(String id) {
        return designationRepository.findById(id).get();
    }

    @Override
    public Designation addEntity(Designation designation) {
        return designationRepository.save(designation);
    }

    @Override
    public void deleteAllEntity() {
        designationRepository.deleteAll();
    }

    @Override
    public void deleteById(String id) {
        designationRepository.deleteById(id);
    }

    public Optional checkDepartmentId(String id){
        return designationRepository.existByDepartmentID(id);
    }
    public List<Designation> getAllByDepartmentId(String departmentId){
        return designationRepository.findByDepartmentId(departmentId);
    }
}
