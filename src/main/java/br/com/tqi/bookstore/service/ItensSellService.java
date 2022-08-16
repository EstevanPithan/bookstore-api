package br.com.tqi.bookstore.service;

import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.model.Author;
import br.com.tqi.bookstore.model.Client;
import br.com.tqi.bookstore.model.ItensSell;
import br.com.tqi.bookstore.repository.ItensSellRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ItensSellService {


    private final ItensSellRepository itensSellRepository;

    private final ClientService clientService;

    public ItensSellService(ItensSellRepository itensSellRepository, ClientService clientService) {
        this.itensSellRepository = itensSellRepository;
        this.clientService = clientService;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly = true)
    public List<ItensSell> findAll() {
        return itensSellRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ItensSell findById(String id) {
        return itensSellRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id)); //Find by id retorna um optinal
    }

    @Transactional
    public ItensSell create(ItensSell itensSellCreate, String clientId) throws NameAlreadyRegisteredException {
        String uuid = getUUID();
        Client client = clientService.findById(clientId);
        itensSellCreate.setId(uuid);
        itensSellCreate.setDate(LocalDateTime.now());
        itensSellRepository.save(itensSellCreate);
        addItensSellOnClientList(itensSellCreate, client);
        return itensSellCreate;
    }

    public void addItensSellOnClientList(ItensSell itensSell, Client client) {
        List<ItensSell> itensSellList = client.getItensSell();
        itensSellList.add(itensSell);
        client.setItensSell(itensSellList);
        clientService.update(client.getId(), client);
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        itensSellRepository.deleteById(id);
    }



}