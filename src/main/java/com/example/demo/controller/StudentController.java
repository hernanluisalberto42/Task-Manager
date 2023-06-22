package com.example.demo.controller;

import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController implements GenericController<StudentDto>{

    private final StudentService service;

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> findAll() {
        return ResponseEntity.ok(
                service.findAllStudents()
                        .stream()
                        .map(StudentDto::from)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                StudentDto.from(
                        service.findStudentById(id)
                )
        );
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StudentDto> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(
                StudentDto.from(
                        service.findStudentByEmail(email)
                )
        );
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDto> save(@Valid @RequestBody StudentDto object) {
        return new ResponseEntity<>(
                StudentDto.from(
                        service.saveStudent(Student.from(object))
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable Long id,@Valid @RequestBody StudentDto object) {
        return ResponseEntity.ok(
                StudentDto.from(
                        service.updateStudent(id,Student.from(object))
                )
        );
    }

    @DeleteMapping("/dell")
    public ResponseEntity<StudentDto> remove(@RequestParam("id") Long id) {
        return ResponseEntity.ok(
                StudentDto.from(
                        service.removeStudent(service.findStudentById(id))
                )
        );
    }

    @PostMapping("/add/{idBook}/{idStudent}")
    public ResponseEntity<StudentDto> addBookToStudent(@PathVariable Long idBook,@PathVariable Long idStudent) {
        return ResponseEntity.ok(
                StudentDto.from(
                        service.addBookToStudent(idBook, idStudent)
                )
        );
    }

    @DeleteMapping("/remove/{idBook}/{idStudent}")
    public ResponseEntity<StudentDto> removeBookFromStudent(@PathVariable Long idBook,@PathVariable Long idStudent) {
        return ResponseEntity.ok(
                StudentDto.from(
                        service.dellBookFromStudent(idBook, idStudent)
                )
        );
    }
}
