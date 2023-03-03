package com.demo.employee.Service;

import com.demo.employee.Model.Employee;
import com.demo.employee.dto.EmployeeDTO;

import java.util.List;
import java.util.UUID;

public interface Services<T> {
    public List<T> getAllEntity();
//    public T addEntity(T t);
    public T getById(String id);
//    public T updateEntity(long id, T t);
    public void deleteAllEntity();
    public void deleteById(String id);
}


