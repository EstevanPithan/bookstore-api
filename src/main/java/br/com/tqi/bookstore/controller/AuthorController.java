package br.com.tqi.bookstore.controller;

import br.com.tqi.bookstore.controller.dto.create.AuthorCreateDTO;
import br.com.tqi.bookstore.controller.dto.AuthorDTO;
import br.com.tqi.bookstore.controller.dto.BookDTO;
import br.com.tqi.bookstore.controller.mapper.AuthorMapper;
import br.com.tqi.bookstore.controller.mapper.BookMapper;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.exception.AuthorCantBeDeleteException;
import br.com.tqi.bookstore.model.Author;
import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@Api(tags = "Author Controller")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;
    public AuthorController(AuthorService authorService, AuthorMapper authorMapper, BookMapper bookMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    @ApiOperation("Find all authors")
    public ResponseEntity<List<AuthorDTO>> findAll(){
        List<Author> authorList = authorService.findAll();
        List<AuthorDTO> result = authorMapper.toAuthorDTOList(authorList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a author by id")
    public ResponseEntity<AuthorDTO> findById(@PathVariable String id){
        Author author = authorService.findById(id);
        AuthorDTO result = authorMapper.toAuthorDTO(author);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a author by id")
    public ResponseEntity delete(@PathVariable String id) throws AuthorCantBeDeleteException {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ApiOperation("Create a new author")
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorCreateDTO dto) throws NameAlreadyRegisteredException {
        Author authorCreate = authorMapper.toAuthorCreate(dto);
        Author author = authorService.create(authorCreate);
        AuthorDTO result = authorMapper.toAuthorDTO(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a author")
    public ResponseEntity<AuthorDTO> update(@PathVariable String id, @RequestBody AuthorCreateDTO dto){
        Author authorUpdate = authorMapper.toAuthorCreate(dto);
        Author author = authorService.update(id, authorUpdate);
        AuthorDTO result = authorMapper.toAuthorDTO(author);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
//    @GetMapping("/books/{id}/")
//    @ApiOperation("Find list of book by author id")
//    public ResponseEntity<List<BookDTO>> booksByAuthor(@PathVariable String id){
//        List<Book> bookList = authorService.getBooksByAuthor(id);
//        List<BookDTO> result = bookMapper.toBookDTOList(bookList);
//        return ResponseEntity.ok(result);
//    }
}
