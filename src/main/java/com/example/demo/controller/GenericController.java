package com.example.demo.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenericController<T>{

    public ResponseEntity<List<T>> findAll();

    public ResponseEntity<T> findById(Long id);

    default ResponseEntity<T> findByEmail(String email){return null;}

    public ResponseEntity<T> save(T object);

    public ResponseEntity<T> update(Long id, T object);

    public ResponseEntity<T> remove(Long id);

    default ResponseEntity<T> addBookToStudent(Long idBook, Long idStudent){return null;}

    default ResponseEntity<T> removeBookFromStudent(Long idBook, Long idStudent){return null;}
}
