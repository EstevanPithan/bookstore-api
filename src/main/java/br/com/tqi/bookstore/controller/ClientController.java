package br.com.tqi.bookstore.controller;


import br.com.tqi.bookstore.controller.dto.ClientDTO;
import br.com.tqi.bookstore.controller.dto.create.ClientCreateDTO;
import br.com.tqi.bookstore.controller.mapper.ClientMapper;
import br.com.tqi.bookstore.exception.CpfAlreadyRegisteredException;
import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.model.Client;
import br.com.tqi.bookstore.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@Api(tags = "Client Controller")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping("/findAll")
    @ApiOperation("Find all clients")
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<Client> clientList = clientService.findAll();
        List<ClientDTO> result = clientMapper.toClientDTOList(clientList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findById/{id}")
    @ApiOperation("Find a client by id")
    public ResponseEntity<ClientDTO> findById(@PathVariable String id) throws IdNotFoundException {
        Client client = clientService.findById(id);
        ClientDTO result = clientMapper.toClientDTO(client);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a client by id")
    public ResponseEntity delete(@PathVariable String id) throws IdNotFoundException {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    @ApiOperation("Create a new client")
    public ResponseEntity<ClientDTO> create(@RequestBody ClientCreateDTO dto) throws CpfAlreadyRegisteredException {
        Client clientCreate = clientMapper.toClientCreate(dto);
        Client client = clientService.create(clientCreate);
        ClientDTO result = clientMapper.toClientDTO(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("Update a client")
    public ResponseEntity<ClientDTO> update(@PathVariable String id, @RequestBody ClientCreateDTO dto) throws IdNotFoundException {
        Client clientUpdate = clientMapper.toClientCreate(dto);
        Client client = clientService.update(id, clientUpdate);
        ClientDTO result = clientMapper.toClientDTO(client);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}