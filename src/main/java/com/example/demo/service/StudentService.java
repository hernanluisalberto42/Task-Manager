package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {

    public List<Student> findAllStudents();

    public Student findStudentById(Long id);

    public Student findStudentByEmail(String email);

    public Student saveStudent(Student student);

    public Student updateStudent(Long id,Student student);

    public Student removeStudent(Student student);

    public Student addBookToStudent(Long idBook, Long idStudent);

    public Student dellBookFromStudent(Long idBook, Long idStudent);
}
