package br.com.tqi.bookstore.controller;

import br.com.tqi.bookstore.controller.dto.create.ItensEntryCreateDTO;
import br.com.tqi.bookstore.controller.dto.ItensEntryDTO;
import br.com.tqi.bookstore.controller.mapper.ItensEntryMapper;
import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.model.ItensEntry;
import br.com.tqi.bookstore.service.ItensEntryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itensEntry")
@Api(tags = "Entry Controller")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItensEntryController {


    private final ItensEntryService itensEntryService;
    private final ItensEntryMapper itensEntryMapper;

    @GetMapping("/findAll")
    @ApiOperation("Find all itensEntrys")
    public ResponseEntity<List<ItensEntryDTO>> findAll(){
        List<ItensEntry> itensEntryList = itensEntryService.findAll();
        List<ItensEntryDTO> result = itensEntryMapper.toItensEntryDTOList(itensEntryList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findById/{id}")
    @ApiOperation("Find a itensEntry by id")
    public ResponseEntity<ItensEntryDTO> findById(@PathVariable String id) throws IdNotFoundException {
        ItensEntry itensEntry = itensEntryService.findById(id);
        ItensEntryDTO result = itensEntryMapper.toItensEntryDTO(itensEntry);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    @ApiOperation("Create a new itensEntry")
    public ResponseEntity<ItensEntryDTO> create(@RequestBody ItensEntryCreateDTO dto) throws IdNotFoundException{
        ItensEntry itensEntryCreate = itensEntryMapper.toItensEntryCreate(dto);
        ItensEntry itensEntry = itensEntryService.create(itensEntryCreate, String.valueOf(dto.getBookId()));
        ItensEntryDTO result = itensEntryMapper.toItensEntryDTO(itensEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}