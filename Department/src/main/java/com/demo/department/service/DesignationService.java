package com.demo.department.service;

import com.demo.department.advice.EntityMappedException;
import com.demo.department.client.EmployeeServiceClient;
import com.demo.department.dao.DepartmentDAO;
import com.demo.department.dao.DesignationDAO;
import com.demo.department.dto.DepartmentDTO;
import com.demo.department.dto.DesignationDTO;
import com.demo.department.dto.EmployeeDTO;
import com.demo.department.model.Department;
import com.demo.department.model.Designation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DesignationService implements Services<DesignationDTO> {
    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private DesignationDAO designationDAO;

    @Autowired
    private EmployeeServiceClient employeeServiceClient;
    @Override
    public List<DesignationDTO> getAllEntity(int offset, int limit) {
        return converDesignationListToDesignationDTOList(designationDAO.getAllEntity(offset, limit));
    }

    @Override
    public DesignationDTO getById(String id) {
        UUID.fromString(id);
        return convertDesignationToDesignationDTO(designationDAO.getById(id));
    }
    public DesignationDTO addEntity(Designation designation){
        UUID.fromString(designation.getDepartmentId());
        designation.setDesignationName(designation.getDesignationName().trim());
        if(designation.getDesignationName().length() < 3)
            throw new NullPointerException();
        if(departmentDAO.getById(designation.getDepartmentId()) != null) {
            designation.setDesignationId(String.valueOf(UUID.randomUUID()));
            return convertDesignationToDesignationDTO(designationDAO.addEntity(designation));
        }
        throw new NoSuchElementException();
    }
    public DesignationDTO updateDesignation(Designation designation, String id){
        if(designation.getDesignationName() == null ||
            designation.getDepartmentId() == null)
            throw new NullPointerException();
        UUID.fromString(id);
        if (departmentDAO.getById(designation.getDepartmentId()) == null)
            throw new NoSuchElementException();
        Designation oldDesignation = designationDAO.getById(id);
        oldDesignation.setDepartmentId(designation.getDepartmentId());
        oldDesignation.setDesignationName(designation.getDesignationName());
        return convertDesignationToDesignationDTO(designationDAO.addEntity(oldDesignation));
    }
    public List<DesignationDTO> getAllDesignationByDepartmentId (String id){
        UUID.fromString(id);
        return converDesignationListToDesignationDTOList(designationDAO.getAllByDepartmentId(id));
    }
    @Override
    public void deleteAllEntity() {
        designationDAO.deleteAllEntity();
    }

    @Override
    public void deleteById(String id) {
        UUID.fromString(id);
        boolean flag = true;
        List<EmployeeDTO> employeeDTOList = employeeServiceClient.getAllEmployee().getBody();
        System.out.println(employeeDTOList);
        for (EmployeeDTO employee : employeeDTOList) {
            if(employee.getDesignationName().equals(designationDAO.getById(id).getDesignationName())){
                flag = false;
                break;
            }
        }
        if(!flag)
            throw new EntityMappedException();
        designationDAO.deleteById(id);
    }
    public List<DesignationDTO> converDesignationListToDesignationDTOList(List<Designation> designationList){
        List<DesignationDTO> listOfDesignationDTO = new ArrayList<>();
        designationList.forEach(designation -> {
            listOfDesignationDTO.add(convertDesignationToDesignationDTO(designation));
        });
        return listOfDesignationDTO;
    }
    public DesignationDTO convertDesignationToDesignationDTO (Designation designation){
        DesignationDTO designationDTO = new DesignationDTO();
//        try {
            designationDTO.setDesignationId(designation.getDesignationId());
            designationDTO.setDesignationName(designation.getDesignationName());
            designationDTO.setDepartmentName(departmentDAO.getById(designation.getDepartmentId()).getDepartmentName());
//        }
//        catch(Exception ex){
//            System.out.println(designation.getDepartmentId());
//        }
        return designationDTO;
    }
}
