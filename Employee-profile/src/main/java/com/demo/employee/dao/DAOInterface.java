package com.demo.employee.dao;

import java.util.List;
import java.util.UUID;

public interface DAOInterface<T> {
    public List<T> getAllEntity();
    public T getById(String id);
    public T addEntity(T t);
    public void deleteAllEntity();
    public void deleteById(String id);
}
