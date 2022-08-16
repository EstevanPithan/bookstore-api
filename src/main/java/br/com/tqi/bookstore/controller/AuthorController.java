package br.com.tqi.bookstore.controller;

import br.com.tqi.bookstore.controller.dto.AuthorCreateDTO;
import br.com.tqi.bookstore.controller.dto.AuthorDTO;
import br.com.tqi.bookstore.controller.mapper.AuthorMapper;
import br.com.tqi.bookstore.exception.NameTitleAlreadyRegisteredException;
import br.com.tqi.bookstore.exception.AuthorCantBeDeleteException;
import br.com.tqi.bookstore.model.Author;
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

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
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
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorCreateDTO dto) throws NameTitleAlreadyRegisteredException {
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
}
