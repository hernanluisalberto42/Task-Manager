package com.example.demo.service;

import com.example.demo.config.CustomNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class StudentServiceImp implements StudentService{

    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;


    @Override
    public List<Student> findAllStudents() {
        return StreamSupport.stream(
                studentRepository.findAll().spliterator(),
                false
        ).collect(Collectors.toList());
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()->new CustomNotFoundException("Not Found...!!!"));
    }

    @Override
    public Student findStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(()->new CustomNotFoundException("Not Found...!!!"));
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        return studentRepository.findById(id)
                .map(obj->{
                    obj.setName(student.getName());
                    obj.setEmail(student.getEmail());
                    return studentRepository.save(obj);
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found...!!!"));
    }

    @Override
    public Student removeStudent(Student student) {
        return studentRepository.findById(student.getIdStudent())
                .map(obj->{
                    studentRepository.delete(obj);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found...!!!"));
    }

    @Override
    @Transactional
    public Student addBookToStudent(Long idBook, Long idStudent) {
        return studentRepository.findById(idStudent)
                .map(student -> {
                    var book=bookRepository.findById(idBook)
                            .orElseThrow(()->new CustomNotFoundException("Not Found...!!!"));
                    student.addBook(book);
                    book.setStudent(student);
                    return student;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found...!!!"));
    }

    @Override
    @Transactional
    public Student dellBookFromStudent(Long idBook, Long idStudent) {
        return studentRepository.findById(idStudent)
                .map(student -> {
                    var book=bookRepository.findById(idBook)
                            .orElseThrow(()->new CustomNotFoundException("Not Found...!!!"));
                    student.removeBook(book);
                    book.setStudent(null);
                    return student;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found...!!!"));
    }
}
