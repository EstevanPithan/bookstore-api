package br.com.tqi.bookstore.controller;


import br.com.tqi.bookstore.controller.dto.create.BookCreateDTO;
import br.com.tqi.bookstore.controller.dto.BookDTO;
import br.com.tqi.bookstore.controller.mapper.BookMapper;
import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Api(tags = "Book Controller")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/findAll")
    @ApiOperation("Find all books")
    public ResponseEntity<List<BookDTO>> findAll(){
        List<Book> bookList = bookService.findAll();
        List<BookDTO> result = bookMapper.toBookDTOList(bookList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findById/{id}")
    @ApiOperation("Find a book by id")
    public ResponseEntity<BookDTO> findById(@PathVariable String id) throws IdNotFoundException {
        Book book = bookService.findById(id);
        BookDTO result = bookMapper.toBookDTO(book);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a book by id")
    public ResponseEntity delete(@PathVariable String id) throws IdNotFoundException{
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    @ApiOperation("Create a new book")
    public ResponseEntity<BookDTO> create(@RequestBody BookCreateDTO dto) throws NameAlreadyRegisteredException, IdNotFoundException {
        Book bookCreate = bookMapper.toBookCreate(dto);
        Book book = bookService.create(bookCreate, String.valueOf(dto.getAuthorId()));
        BookDTO result = bookMapper.toBookDTO(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("Update a book")
    public ResponseEntity<BookDTO> update(@PathVariable String id, @RequestBody BookCreateDTO dto) throws IdNotFoundException{
        Book bookUpdate = bookMapper.toBookCreate(dto);
        Book book = bookService.update(id, bookUpdate);
        BookDTO result = bookMapper.toBookDTO(book);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
