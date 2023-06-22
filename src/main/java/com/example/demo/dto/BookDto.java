package com.example.demo.dto;

import com.example.demo.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    private Long idBook;
    private String name;
    private StudentDto studentDto;

    public static BookDto from(Book book){
        BookDto bookDto=new BookDto();
        bookDto.setIdBook(book.getIdBook());
        bookDto.setName(book.getNameBook());
        if(Objects.nonNull(book.getStudent()))
            bookDto.setStudentDto(StudentDto.from(book.getStudent()));
        return bookDto;
    }
}
