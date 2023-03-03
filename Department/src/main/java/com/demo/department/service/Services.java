package com.demo.department.service;

//import com.department.demo.model.T;

import java.util.List;

public interface Services<T> {
    public List<T> getAllEntity();
    public T getById(String id);
    public void deleteAllEntity();
    public void deleteById(String id);
}
