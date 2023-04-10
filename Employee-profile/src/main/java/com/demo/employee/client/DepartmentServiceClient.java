package com.demo.employee.client;

import com.demo.employee.dto.DesignationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-MICROSERVICE",configuration = ServiceClientConfiguration.class)
public interface DepartmentServiceClient {
    @GetMapping("designations/{id}")
    DesignationDTO getDesignationById(@PathVariable String id);
}

