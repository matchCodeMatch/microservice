package com.demo.department.service;

import com.demo.department.advice.EntityMappedException;
import com.demo.department.dao.DepartmentDAO;
import com.demo.department.dao.DesignationDAO;
import com.demo.department.dto.DepartmentDTO;
import com.demo.department.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DepartmentService implements Services<DepartmentDTO>{


    @Autowired
    private DepartmentDAO departmentDAO;
    @Autowired
    private DesignationDAO designationDAO;

    @Override
    public List<DepartmentDTO> getAllEntity() {
        return converDepartmentListToDepartmentDTOList(departmentDAO.getAllEntity());
    }

    @Override
    public DepartmentDTO getById(String id) {
         UUID.fromString(id);
         return convertDepartmentToDepartmentDTO(departmentDAO.getById(id));
    }

    public DepartmentDTO addEntity(Department department) {
        department.setDepartmentName(department.getDepartmentName().trim());
        if(department.getDepartmentName() == null ||  department.getDepartmentName().length() < 3)
            throw new NullPointerException();
        department.setDepartmentId(String.valueOf(UUID.randomUUID()));
        return convertDepartmentToDepartmentDTO(departmentDAO.addEntity(department));
    }
    public DepartmentDTO updateDepartment(Department department, String id){
        if (department.getDepartmentName() == null)
            throw new NullPointerException();
        UUID.fromString(id);
        Department oldDepartment = departmentDAO.getById(id);
        oldDepartment.setDepartmentName(department.getDepartmentName());
        return convertDepartmentToDepartmentDTO(departmentDAO.addEntity(oldDepartment));
    }
    @Override
    public void deleteAllEntity() {
        departmentDAO.deleteAllEntity();
    }

    @Override
    public void deleteById(String id) {
        UUID.fromString(id);
        if(designationDAO.checkDepartmentId(id).isEmpty())
            departmentDAO.deleteById(id);
        else
            throw new EntityMappedException();
    }
    public List<DepartmentDTO> converDepartmentListToDepartmentDTOList(List<Department> departmentList){
        List<DepartmentDTO> listOfDepartmentDTO = new ArrayList<>();
        departmentList.forEach(department -> {
            listOfDepartmentDTO.add(convertDepartmentToDepartmentDTO(department));
        });
        return listOfDepartmentDTO;
    }
    public DepartmentDTO convertDepartmentToDepartmentDTO (Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(department.getDepartmentId());
        departmentDTO.setDepartmentName(department.getDepartmentName());
        return departmentDTO;
    }
}
