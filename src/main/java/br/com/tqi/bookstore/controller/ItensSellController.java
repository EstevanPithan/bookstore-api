package br.com.tqi.bookstore.controller;

import br.com.tqi.bookstore.controller.dto.ItensSellDTO;
import br.com.tqi.bookstore.controller.dto.create.ItensSellCreateDTO;
import br.com.tqi.bookstore.controller.mapper.ItensSellMapper;
import br.com.tqi.bookstore.exception.BookQuantityNotEnougthToSellException;
import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.model.ItensSell;
import br.com.tqi.bookstore.service.ItensSellService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sell")
@Api(tags = "Sells Controller")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItensSellController {

    private final ItensSellService itensSellService;
    private final ItensSellMapper itensSellMapper;

    @GetMapping("/findAll")
    @ApiOperation("Find all itensSells")
    public ResponseEntity<List<ItensSellDTO>> findAll() {
        List<ItensSell> itensSellList = itensSellService.findAll();
        List<ItensSellDTO> result = itensSellMapper.toItensSellDTOList(itensSellList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findById/{id}")
    @ApiOperation("Find a itensSell by id")
    public ResponseEntity<ItensSellDTO> findById(@PathVariable String id) throws IdNotFoundException {
        ItensSell itensSell = itensSellService.findById(id);
        ItensSellDTO result = itensSellMapper.toItensSellDTO(itensSell);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    @ApiOperation("Create a new itensSell")
    public ResponseEntity<ItensSellDTO> create(@RequestBody ItensSellCreateDTO dto) throws IdNotFoundException, NameAlreadyRegisteredException, BookQuantityNotEnougthToSellException {
        ItensSell itensSellCreate = itensSellMapper.toItensSellCreate(dto);
        ItensSell itensSell = itensSellService.create(itensSellCreate);
        ItensSellDTO result = itensSellMapper.toItensSellDTO(itensSell);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
