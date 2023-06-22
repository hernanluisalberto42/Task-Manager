package com.example.demo.dto;

import com.example.demo.model.Book;
import com.example.demo.model.Student;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDtoPlain {

    private Long idBook;
    private String name;

    public static BookDtoPlain from(Book book){
        return BookDtoPlain
                .builder()
                .idBook(book.getIdBook())
                .name(book.getNameBook())
                .build();
    }


}
