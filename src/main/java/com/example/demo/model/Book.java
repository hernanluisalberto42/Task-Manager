package com.example.demo.model;

import com.example.demo.dto.BookDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "book_tb")
public class Book implements Serializable {

    private static final Long serialVersionUID=1L;

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(name = "id_book")
    private Long idBook;


    @NotEmpty
    @Column(name = "name_book")
    private String nameBook;

    @ManyToOne
    private Student student;

    public static Book from(BookDto bookDto){
        Book book=new Book();
        book.setNameBook(bookDto.getName());
        return book;
    }
}
