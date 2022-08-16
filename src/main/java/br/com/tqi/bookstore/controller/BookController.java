package br.com.tqi.bookstore.controller;


import br.com.tqi.bookstore.controller.dto.BookCreateDTO;
import br.com.tqi.bookstore.controller.dto.BookDTO;
import br.com.tqi.bookstore.controller.mapper.BookMapper;
import br.com.tqi.bookstore.exception.NameTitleAlreadyRegisteredException;
import br.com.tqi.bookstore.model.Author;
import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.service.AuthorService;
import br.com.tqi.bookstore.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Api(tags = "Book Controller")
public class BookController {


    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    @ApiOperation("Find all books")
    public ResponseEntity<List<BookDTO>> findAll(){
        List<Book> bookList = bookService.findAll();
        List<BookDTO> result = bookMapper.toBookDTOList(bookList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a book by id")
    public ResponseEntity<BookDTO> findById(@PathVariable String id){
        Book book = bookService.findById(id);
        BookDTO result = bookMapper.toBookDTO(book);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("Delete a book by id")
    public ResponseEntity delete(@PathVariable String id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ApiOperation("Create a new book")
    public ResponseEntity<BookDTO> create(@RequestBody BookCreateDTO dto) throws NameTitleAlreadyRegisteredException {
        Book bookCreate = bookMapper.toBookCreate(dto);
        Book book = bookService.create(bookCreate, String.valueOf(dto.getAuthor()));
        BookDTO result = bookMapper.toBookDTO(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a book")
    public ResponseEntity<BookDTO> update(@PathVariable String id, @RequestBody BookCreateDTO dto){
        Book bookUpdate = bookMapper.toBookCreate(dto);
        Book book = bookService.update(id, bookUpdate);
        BookDTO result = bookMapper.toBookDTO(book);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
