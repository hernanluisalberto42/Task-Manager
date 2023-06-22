package com.example.demo.model;

import com.example.demo.dto.StudentDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "student",uniqueConstraints = @UniqueConstraint(
        name = "email",
        columnNames = "email"
))
public class Student implements Serializable {

    private static final Long serialVersionUID=1L;

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(name = "id_student")
    private Long idStudent;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "idStudent",
            referencedColumnName = "id_student"
    )
    private List<Book>bookList;

    public void addBook(Book book){
        this.bookList.add(book);
    }

    public void removeBook(Book book){
        this.bookList.remove(book);
    }

    public static Student from(StudentDto studentDto){
        Student student=new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        return student;
    }

}
