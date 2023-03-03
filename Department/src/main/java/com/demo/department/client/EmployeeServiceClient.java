package com.demo.department.client;

import com.demo.department.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "EMPLOYEE-MICROSERVICE")
public interface EmployeeServiceClient {
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee();
}
