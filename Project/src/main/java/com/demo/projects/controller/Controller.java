package com.demo.projects.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Controller<T> {

    public ResponseEntity<List<T>> getAllEntity();
    public ResponseEntity<T> getById(List<String> id);
    public ResponseEntity<T> addEntity(T t);
    public void deleteAllEntity();
    public void deleteById(String id);
}
