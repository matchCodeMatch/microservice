package com.demo.projects.dao;

import java.util.List;

public interface DAOInterface<T> {
    public List<T> getAllEntity();
    public T getById(String id);
    public T addEntity(T t);
    public void deleteAllEntity();
    public void deleteById(String id);
}

