package br.com.tqi.bookstore.controller;

import br.com.tqi.bookstore.controller.dto.create.AuthorCreateDTO;
import br.com.tqi.bookstore.controller.dto.AuthorDTO;
import br.com.tqi.bookstore.controller.dto.BookDTO;
import br.com.tqi.bookstore.controller.mapper.AuthorMapper;
import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.model.Author;
import br.com.tqi.bookstore.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@Api(tags = "Author Controller")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @GetMapping("/findAll")
    @ApiOperation("Find all authors")
    public ResponseEntity<List<AuthorDTO>> findAll(){
        List<Author> authorList = authorService.findAll();
        List<AuthorDTO> result = authorMapper.toAuthorDTOList(authorList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findById/{id}")
    @ApiOperation("Find a author by id")
    public ResponseEntity<AuthorDTO> findById(@PathVariable String id) throws IdNotFoundException {
        Author author = authorService.findById(id);
        AuthorDTO result = authorMapper.toAuthorDTO(author);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a author by id")
    public ResponseEntity delete(@PathVariable String id) throws IdNotFoundException {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    @ApiOperation("Create a new author")
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorCreateDTO dto) throws NameAlreadyRegisteredException {
        Author authorCreate = authorMapper.toAuthorCreate(dto);
        Author author = authorService.create(authorCreate);
        AuthorDTO result = authorMapper.toAuthorDTO(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("Update a author")
    public ResponseEntity<AuthorDTO> update(@PathVariable String id, @RequestBody AuthorCreateDTO dto)throws IdNotFoundException{
        Author authorUpdate = authorMapper.toAuthorCreate(dto);
        Author author = authorService.update(id, authorUpdate);
        AuthorDTO result = authorMapper.toAuthorDTO(author);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/books/{id}/")
    @ApiOperation("Find list of book by author id")
    public ResponseEntity<List<BookDTO>> booksByAuthor(@PathVariable String id) throws IdNotFoundException{
        return ResponseEntity.ok(authorService.getBooksByAuthor(id));
    }
}
