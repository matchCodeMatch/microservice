package com.demo.projects.service;

import java.util.List;

public interface Services<T> {
    public List<T> getAllEntity();
    public T getById(String id);
    public void deleteAllEntity();
    public void deleteById(String id);
}
