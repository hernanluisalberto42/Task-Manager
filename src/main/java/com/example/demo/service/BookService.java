package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Student;

import java.util.List;

public interface BookService {

    public List<Book> findAllBooks();

    public Book findBookById(Long id);

    public Book saveBook(Book book);

    public Book updateBook(Long id,Book book);

    public Book removeBook(Book book);
}
