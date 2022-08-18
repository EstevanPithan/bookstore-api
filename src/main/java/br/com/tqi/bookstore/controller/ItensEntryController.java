package br.com.tqi.bookstore.controller;

import br.com.tqi.bookstore.controller.dto.create.ItensEntryCreateDTO;
import br.com.tqi.bookstore.controller.dto.ItensEntryDTO;
import br.com.tqi.bookstore.controller.mapper.ItensEntryMapper;
import br.com.tqi.bookstore.exception.BookQuantityNotEnougthToReverseException;
import br.com.tqi.bookstore.model.ItensEntry;
import br.com.tqi.bookstore.service.ItensEntryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itensEntry")
@Api(tags = "Entry Controller")
public class ItensEntryController {


    private final ItensEntryService itensEntryService;
    private final ItensEntryMapper itensEntryMapper;

    public ItensEntryController(ItensEntryService itensEntryService, ItensEntryMapper itensEntryMapper) {
        this.itensEntryService = itensEntryService;
        this.itensEntryMapper = itensEntryMapper;
    }

    @GetMapping
    @ApiOperation("Find all itensEntrys")
    public ResponseEntity<List<ItensEntryDTO>> findAll(){
        List<ItensEntry> itensEntryList = itensEntryService.findAll();
        List<ItensEntryDTO> result = itensEntryMapper.toItensEntryDTOList(itensEntryList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a itensEntry by id")
    public ResponseEntity<ItensEntryDTO> findById(@PathVariable String id){
        ItensEntry itensEntry = itensEntryService.findById(id);
        ItensEntryDTO result = itensEntryMapper.toItensEntryDTO(itensEntry);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("Delete a itensEntry by id")
    public ResponseEntity reverseAndDelete(@PathVariable String id) throws BookQuantityNotEnougthToReverseException {
        itensEntryService.reverseAndDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ApiOperation("Create a new itensEntry")
    public ResponseEntity<ItensEntryDTO> create(@RequestBody ItensEntryCreateDTO dto) {
        ItensEntry itensEntryCreate = itensEntryMapper.toItensEntryCreate(dto);
        ItensEntry itensEntry = itensEntryService.create(itensEntryCreate, String.valueOf(dto.getBookId()));
        ItensEntryDTO result = itensEntryMapper.toItensEntryDTO(itensEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}