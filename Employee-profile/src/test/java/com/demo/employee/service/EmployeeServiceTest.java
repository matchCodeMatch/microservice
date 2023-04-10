package com.demo.employee.service;

import com.demo.employee.Advice.EntityMappedException;
import com.demo.employee.Model.Employee;
import com.demo.employee.Model.EmployeeProjectMapping;
import com.demo.employee.Service.EmployeeProjectMappingService;
import com.demo.employee.Service.EmployeeService;
import com.demo.employee.client.DepartmentServiceClient;
import com.demo.employee.client.ProjectServiceClient;
import com.demo.employee.dao.*;
import com.demo.employee.dto.DesignationDTO;
import com.demo.employee.dto.EmployeeDTO;
import com.demo.employee.dto.TaskDTO;
import feign.FeignException;
import feign.RequestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.*;

import static com.demo.employee.Model.Employee.Gender;
import static com.demo.employee.dto.TaskDTO.Status.NOT_STARTED;


@SpringBootTest
public class EmployeeServiceTest{
    @InjectMocks
    EmployeeService employeeService;
    @Mock
    EmployeeDAO employeeDAO;
    @Mock
    DepartmentServiceClient departmentServiceClient;
    @Mock
    ProjectServiceClient projectServiceClient;
    @Mock
    EmployeeProjectMappingDAO employeeProjectMappingDAO;

    Employee employee1 = new Employee(
                        "2d77a7e3-1c68-4df9-9c88-51f35a3e712a",
                        "John",
                        "Doe",
                         Gender.MALE,
                        "john.doe@example.com",
                         LocalDate.of(1990, 5, 10),
                         LocalDate.now(),
                        50000.0,
                        1234567890,
                        "a9a20a7d-e8d3-4c88-bc06-2d7fe1c325bf");
    Employee employee2 = new Employee(
                        "2d77a7e3-1c68-4df9-9c88-51f35a3e712b",
                        "David",
                        "Doedy",
                        Gender.MALE,
                        "david.doe@example.com",
                        LocalDate.of(1990, 7, 30),
                        LocalDate.now(),
                        50000.0,
                        1234567890,
                        "a9a20a7d-e8d3-4c88-bc06-2d7fe1c325bf");
    List<Employee> listOfEmployees = new ArrayList<>(Arrays.asList(employee1,employee2));
    DesignationDTO designationDTO = new DesignationDTO(
                        "a9a20a7d-e8d3-4c88-bc06-2d7fe1c325bf",
                        "Manager",
                        "IT");
    @Test
    public void testGetAllEntity(){
        Mockito.when(departmentServiceClient.getDesignationById("a9a20a7d-e8d3-4c88-bc06-2d7fe1c325bf")).thenReturn(designationDTO);
        Mockito.when(employeeDAO.getAllEntity(0,10)).thenReturn(listOfEmployees);

        List<EmployeeDTO> list = employeeService.getAllEntity(0,10);
        Assertions.assertEquals(2,list.size());
    }
    @Test
    public void testGetByCorrectId(){
        Mockito.when(employeeDAO.getById("2d77a7e3-1c68-4df9-9c88-51f35a3e712a")).thenReturn(employee1);
        Mockito.when(departmentServiceClient.getDesignationById("a9a20a7d-e8d3-4c88-bc06-2d7fe1c325bf")).thenReturn(designationDTO);

        Assertions.assertNotNull(employeeService.getById("2d77a7e3-1c68-4df9-9c88-51f35a3e712a"));
    }
    @Test
    public void testGetByWrongId(){
        Assertions.assertThrows(NullPointerException.class,()-> employeeService.getById("2d77a7e3-1c68-4df9-9c88-51f35a3e712"));
    }
    @Test
    public void testGetByWrongFormat(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> employeeService.getById("1"));
    }
    @Test
    public void testAddEntityCorrect(){
        Mockito.when(employeeDAO.addEntity(employee1)).thenReturn(employee1);
        Mockito.when(departmentServiceClient.getDesignationById("a9a20a7d-e8d3-4c88-bc06-2d7fe1c325bf")).thenReturn(designationDTO);

        Assertions.assertNotNull(employeeService.addEntity(employee1));
    }
    @Test
    public void testAddEntityWithFirstName(){
        String firstName = employee1.getFirstName();
        employee1.setFirstName(null);
        Assertions.assertThrows(NullPointerException.class,() -> employeeService.addEntity(employee1));
        employee1.setFirstName(firstName);
    }
    @Test
    public void testAddEntityWithHireDate(){
        LocalDate hireDate = employee1.getHireDate();
        employee1.setHireDate(null);
        Assertions.assertThrows(NullPointerException.class,() -> employeeService.addEntity(employee1));
        employee1.setHireDate(hireDate);
    }
//    @Test
//    public void testAddEntityWithDesignationId(){
////        String designationID = employee1.getDesignationId();
////        employee1.setDesignationId(null);
////        Assertions.assertThrows(NullPointerException.class,() -> employeeService.addEntity(employee1));
////        employee1.setDesignationId(designationID);
//
//        feign.Request.create();
//
//        Mockito.when(departmentServiceClient.getDesignationById("f2b68d1b-c46b-4d84-ba2b-5c93a5a829d5"))
//                .thenThrow(new FeignException.BadRequest("Id not found", ));
//        Assertions.assertThrows(FeignException.class,()->departmentServiceClient.getDesignationById("f2b68d1b-c46b-4d84-ba2b-5c93a5a829d5"));
//    }
    @Test
    public void testAddEntityWithSalary(){
        Double salary = employee1.getSalary();
        employee1.setSalary(null);
        Assertions.assertThrows(NullPointerException.class,() -> employeeService.addEntity(employee1));
        employee1.setSalary(salary);
    }
    @Test
    public void testAddEntityWithEmail(){
        String email = employee1.getEmail();
        employee1.setEmail("adjhaldihcaldkhc");
        Assertions.assertThrows(NullPointerException.class,() -> employeeService.addEntity(employee1));
        employee1.setEmail(email);
    }
    @Test
    public void testGetByCorrectDesignationId(){
        String expectedDestId = "2d77a7e3-1c68-4df9-9c88-51f35a3e712a";
        Mockito.when(employeeDAO.getAllByDesignationId(expectedDestId))
                .thenReturn(listOfEmployees);
        Mockito.when(departmentServiceClient.getDesignationById("a9a20a7d-e8d3-4c88-bc06-2d7fe1c325bf"))
                .thenReturn(designationDTO);
        Assertions.assertEquals(2,employeeService.getByDesignationId(expectedDestId).size());
    }
    @Test
    public void testGetByDesignationIdWrongFormat(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> employeeService.getByDesignationId("1"));
    }
    @Test
    public void testUpdateEntity(){
        Mockito.when(employeeDAO.getById("2d77a7e3-1c68-4df9-9c88-51f35a3e712a")).thenReturn(employee1);
        Mockito.when(employeeDAO.addEntity(employee1)).thenReturn(null);
        Mockito.when(departmentServiceClient.getDesignationById("a9a20a7d-e8d3-4c88-bc06-2d7fe1c325bf")).thenReturn(designationDTO);
        Assertions.assertEquals(employee2.getFirstName(),employeeService.updateEntity(
                "2d77a7e3-1c68-4df9-9c88-51f35a3e712a",
                employee2,
                true
                ).getFirstName());
    }
    @Test
    public void testUpdateEntityWithWrongFormatId(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> employeeService.updateEntity("1",employee1,true));
    }
    @Test
    public void testDeleteByIdWithWrongFormat(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> employeeService.deleteById("1"));
    }

    @Test
    public void testDeleteById() {
        String uuid = "3c899e87-72c4-4a01-8c95-97b7437e1d24";
        List<TaskDTO> taskDTOList = new ArrayList<>();
        taskDTOList.add(new TaskDTO("12345", "Design new logo", "Marketing campaign", "John Smith",NOT_STARTED));
        Mockito.when(projectServiceClient.getAllTasks()).thenReturn(new ResponseEntity<List<TaskDTO>>(taskDTOList, HttpStatus.OK));
        Mockito.when(employeeDAO.getById(uuid)).thenReturn(employee1);
        Mockito.when(employeeProjectMappingDAO.checkByEmployeeId(uuid)).thenReturn(Optional.empty());
        employeeService.deleteById(uuid);
        Mockito.verify(employeeDAO).deleteById(uuid);
    }
    @Test
    public void testDeleteByIdMappedWithTask() {
        String uuid = "3c899e87-72c4-4a01-8c95-97b7437e1d24";
        List<TaskDTO> taskDTOList = new ArrayList<>();
        taskDTOList.add(new TaskDTO("12345", "Design new logo", "Marketing campaign", "John",NOT_STARTED));
        Mockito.when(projectServiceClient.getAllTasks()).thenReturn(new ResponseEntity<List<TaskDTO>>(taskDTOList, HttpStatus.OK));
        Mockito.when(employeeDAO.getById(uuid)).thenReturn(employee1);
        Mockito.when(employeeProjectMappingDAO.checkByEmployeeId(uuid)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityMappedException.class,()->employeeService.deleteById(uuid));
    }
    @Test
    public void testDeleteByIdMappedWithProject() {
        String uuid = "3c899e87-72c4-4a01-8c95-97b7437e1d24";
        List<TaskDTO> taskDTOList = new ArrayList<>();
        taskDTOList.add(new TaskDTO("12345", "Design new logo", "Marketing campaign", "John",NOT_STARTED));
        Mockito.when(projectServiceClient.getAllTasks()).thenReturn(new ResponseEntity<List<TaskDTO>>(taskDTOList, HttpStatus.OK));
        Mockito.when(employeeDAO.getById(uuid)).thenReturn(employee2);
        Mockito.when(employeeProjectMappingDAO.checkByEmployeeId(uuid)).thenReturn(Optional.of("Employee Mapped with Project"));
        Assertions.assertThrows(EntityMappedException.class,()->employeeService.deleteById(uuid));
    }
    @Test
    public void testSomething(){
        Assertions.assertEquals(5,5);
    }
}