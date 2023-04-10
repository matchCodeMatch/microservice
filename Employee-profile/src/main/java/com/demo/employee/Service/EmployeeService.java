package com.demo.employee.Service;

import com.demo.employee.Advice.EntityMappedException;
import com.demo.employee.client.ProjectServiceClient;
import com.demo.employee.client.DepartmentServiceClient;
import com.demo.employee.dao.EmployeeDAO;
import com.demo.employee.dao.EmployeeProjectMappingDAO;
import com.demo.employee.dto.EmployeeDTO;
import com.demo.employee.dto.TaskDTO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.demo.employee.Model.Employee;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class EmployeeService implements Services<EmployeeDTO> {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private EmployeeProjectMappingDAO employeeProjectMappingDAO;
    @Autowired
    private DepartmentServiceClient departmentServiceClient;
    @Autowired
    private ProjectServiceClient projectServiceClient;

    @Override
    public List<EmployeeDTO> getAllEntity(int offset,int limit) {
        return convertEmployeeListToEmployeeDTOList(employeeDAO.getAllEntity(offset,limit));
    }
    public EmployeeDTO addEntity(Employee employee) {
        if(employee.getFirstName() == null ||
                employee.getHireDate() == null ||
                employee.getDesignationId() == null ||
                employee.getSalary() < 1 ||
                !EmailValidator.getInstance().isValid(employee.getEmail())
                )
            throw new NullPointerException("Invalid Field format");
        UUID.fromString(employee.getDesignationId());
        departmentServiceClient.getDesignationById(employee.getDesignationId());
        employee.setEmployeeId(String.valueOf(UUID.randomUUID()));
        return convertEmployeeToEmployeeDTO(employeeDAO.addEntity(employee));
    }
    @Override
    public EmployeeDTO getById(String id) {
        UUID.fromString(id);
        return convertEmployeeToEmployeeDTO(employeeDAO.getById(id));
    }
    public List<EmployeeDTO> getByDesignationId(String designationId){
        UUID.fromString(designationId);
        return convertEmployeeListToEmployeeDTOList(employeeDAO.getAllByDesignationId(designationId));
    }
    public EmployeeDTO updateEntity(String id, Employee employee,boolean flag) {
        UUID.fromString(id);
        Employee oldEmployee = employeeDAO.getById(id);
        if (employee.getFirstName() != null  || flag)
            oldEmployee.setFirstName(employee.getFirstName());
        if (employee.getLastName() != null  || flag)
            oldEmployee.setLastName(employee.getLastName());
        if (employee.getDob() != null  || flag)
            oldEmployee.setDob(employee.getDob());
        if (employee.getGender() != null  || flag)
            oldEmployee.setGender(employee.getGender());
        if (employee.getEmail() != null  || flag)
            oldEmployee.setEmail(employee.getEmail());
        if (employee.getHireDate() != null  || flag)
            oldEmployee.setHireDate(employee.getHireDate());
        if (employee.getSalary() != null  || flag)
            oldEmployee.setSalary(employee.getSalary());
        if (employee.getPhNumber() != null  || flag)
            oldEmployee.setPhNumber(employee.getPhNumber());
        employeeDAO.addEntity(oldEmployee);
        return convertEmployeeToEmployeeDTO(oldEmployee);
    }
//    @Override
//    public void deleteAllEntity() {
//        employeeDAO.deleteAllEntity();
//    }
    @Override
    public void deleteById(String id) {
        UUID.fromString(id);
        boolean flag = true;
        List<TaskDTO> taskDTOList;
        taskDTOList = projectServiceClient.getAllTasks().getBody();
        String firstName =  employeeDAO.getById(id).getFirstName();
        for(TaskDTO taskDTO : taskDTOList){
            if(taskDTO.getEmployeeName().equals(firstName)){
                flag = false;
                break;
            }
        }
        if(!flag || employeeProjectMappingDAO.checkByEmployeeId(id).isPresent())
            throw new EntityMappedException();
        employeeDAO.deleteById(id);
    }
    public List<EmployeeDTO> convertEmployeeListToEmployeeDTOList (List<Employee> employeeList){
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeList.forEach(employee -> employeeDTOList.add(convertEmployeeToEmployeeDTO(employee)));
        return employeeDTOList;
    }
    public EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.setDob(employee.getDob());
        empDTO.setGender(employee.getGender());
        empDTO.setEmail(employee.getEmail());
        empDTO.setEmployeeId(employee.getEmployeeId());
        empDTO.setFirstName(employee.getFirstName());
        empDTO.setSalary(employee.getSalary());
        empDTO.setHireDate(employee.getHireDate());
        empDTO.setPhNumber(employee.getPhNumber());
        empDTO.setLastName(employee.getLastName());
        empDTO.setDesignationName(
                departmentServiceClient.getDesignationById(
                        employee.getDesignationId()
                ).getDesignationName());

        return empDTO;
    }
}