package com.example.demo.dto;

import com.example.demo.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {

    private Long idStudent;
    private String name;
    private String email;
    private List<BookDtoPlain> bookList;

    public static StudentDto from(Student student){
        StudentDto studentDto=new StudentDto();
        studentDto.setIdStudent(student.getIdStudent());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        if(Objects.nonNull(student.getBookList()))
            studentDto.setBookList(
                    student.getBookList()
                            .stream()
                            .map(BookDtoPlain::from)
                            .collect(Collectors.toList())
            );
        return studentDto;
    }

}
