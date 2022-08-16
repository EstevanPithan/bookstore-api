package br.com.tqi.bookstore.controller;

import br.com.tqi.bookstore.controller.dto.ItensSellDTO;
import br.com.tqi.bookstore.controller.dto.create.ItensSellCreateDTO;
import br.com.tqi.bookstore.controller.mapper.ItensSellMapper;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.model.ItensSell;
import br.com.tqi.bookstore.service.ItensSellService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sell")
@Api(tags = "Sells Controller")
public class ItensSellController {

    private final ItensSellService itensSellService;
    private final ItensSellMapper itensSellMapper;

    public ItensSellController(ItensSellService itensSellService, ItensSellMapper itensSellMapper) {
        this.itensSellService = itensSellService;
        this.itensSellMapper = itensSellMapper;
    }

    @GetMapping
    @ApiOperation("Find all itensSells")
    public ResponseEntity<List<ItensSellDTO>> findAll(){
        List<ItensSell> itensSellList = itensSellService.findAll();
        List<ItensSellDTO> result = itensSellMapper.toItensSellDTOList(itensSellList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a itensSell by id")
    public ResponseEntity<ItensSellDTO> findById(@PathVariable String id){
        ItensSell itensSell = itensSellService.findById(id);
        ItensSellDTO result = itensSellMapper.toItensSellDTO(itensSell);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("Delete a itensSell by id")
    public ResponseEntity delete(@PathVariable String id){
        itensSellService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ApiOperation("Create a new itensSell")
    public ResponseEntity<ItensSellDTO> create(@RequestBody ItensSellCreateDTO dto) throws NameAlreadyRegisteredException {
        ItensSell itensSellCreate = itensSellMapper.toItensSellCreate(dto);
        ItensSell itensSell = itensSellService.create(itensSellCreate, String.valueOf(dto.getClient()));
        ItensSellDTO result = itensSellMapper.toItensSellDTO(itensSell);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
