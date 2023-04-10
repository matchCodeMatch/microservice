package com.demo.department.dao;

import java.util.List;

public interface DAOInterface<T> {
    public List<T> getAllEntity(int offset, int limit);
    public T getById(String idList);
    public T addEntity(T t);
    public void deleteAllEntity();
    public void deleteById(String id);
}
