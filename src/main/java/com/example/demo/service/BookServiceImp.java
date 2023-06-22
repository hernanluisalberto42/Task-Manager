package com.example.demo.service;

import com.example.demo.config.CustomNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService{

    private final BookRepository bookRepository;


    @Override
    public List<Book> findAllBooks() {
        return StreamSupport.stream(
                bookRepository.findAll().spliterator(),
                false
        ).collect(Collectors.toList());
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()->new CustomNotFoundException("Not Found!!.."));
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        return bookRepository.findById(id)
                .map(obj->{
                    obj.setNameBook(book.getNameBook());
                    return bookRepository.save(obj);
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found!!.."));
    }

    @Override
    public Book removeBook(Book book) {
        return bookRepository.findById(book.getIdBook())
                .map(obj->{
                    bookRepository.delete(obj);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found!!.."));
    }
}
