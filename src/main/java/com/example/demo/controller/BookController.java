package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController implements GenericController<BookDto>{

    private final BookService service;

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.ok(
                service.findAllBooks()
                        .stream()
                        .map(BookDto::from)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                BookDto.from(
                        service.findBookById(id)
                )
        );
    }

    @PostMapping("/create")
    public ResponseEntity<BookDto> save(@Valid @RequestBody BookDto object) {
        return new ResponseEntity<>(
                BookDto.from(
                        service.saveBook(Book.from(object))
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id,@Valid @RequestBody BookDto object) {
        return ResponseEntity.ok(
                BookDto.from(
                        service.updateBook(id,Book.from(object))
                )
        );
    }

    @DeleteMapping("/dell")
    public ResponseEntity<BookDto> remove(@RequestParam("id") Long id) {
        return ResponseEntity.ok(
                BookDto.from(
                        service.removeBook(service.findBookById(id))
                )
        );
    }
}
