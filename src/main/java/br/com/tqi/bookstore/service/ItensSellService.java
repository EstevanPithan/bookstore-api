package br.com.tqi.bookstore.service;

import br.com.tqi.bookstore.controller.dto.create.ItensSellCreateDTO;
import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.model.Client;
import br.com.tqi.bookstore.model.ItensSell;
import br.com.tqi.bookstore.repository.ItensSellRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ItensSellService {


    private final ItensSellRepository itensSellRepository;

    private final ClientService clientService;

    private final BookService bookService;

    public ItensSellService(ItensSellRepository itensSellRepository, ClientService clientService, BookService bookService) {
        this.itensSellRepository = itensSellRepository;
        this.clientService = clientService;
        this.bookService = bookService;
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
    public ItensSell create(ItensSell itensSellCreate, ItensSellCreateDTO dto) throws NameAlreadyRegisteredException {
        String uuid = getUUID();

        Client client = clientService.findById(String.valueOf(dto.getClientId()));

        itensSellCreate.setId(uuid);
        itensSellCreate.setDate(LocalDateTime.now());
        itensSellRepository.save(itensSellCreate);
        addItensSellOnClientList(itensSellCreate, client);
        subtractBookFromStock(itensSellCreate);
        return itensSellCreate;
    }

    @Transactional
    public void addItensSellOnClientList(ItensSell itensSell, Client client) {
        List<ItensSell> itensSellList = client.getItensSell();
        itensSellList.add(itensSell);
        client.setItensSell(itensSellList);
        clientService.update(client.getId(), client);
    }

    @Transactional
    public void subtractBookFromStock(ItensSell itensSell) {
        String[] idArray = itensSell.getBookIds();
        Integer[] qntArray = itensSell.getBooksQnt();
        for (int i = 0; i < itensSell.getBooksQnt().length; i++) {
            Book book = bookService.findById(idArray[i]);
            book.setQuantity(book.getQuantity() - qntArray[i]);
            bookService.update(book.getId(), book);
        }
    }


    @Transactional
    public void delete(String id) {
        findById(id);
        itensSellRepository.deleteById(id);
    }


}