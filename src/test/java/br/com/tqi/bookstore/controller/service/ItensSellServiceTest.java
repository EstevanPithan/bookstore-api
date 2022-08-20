package br.com.tqi.bookstore.controller.service;

import br.com.tqi.bookstore.controller.builder.BookBuilder;
import br.com.tqi.bookstore.controller.builder.ClientBuilder;
import br.com.tqi.bookstore.controller.builder.ItensEntryBuilder;
import br.com.tqi.bookstore.controller.builder.ItensSellBuilder;
import br.com.tqi.bookstore.controller.mapper.ItensEntryMapper;
import br.com.tqi.bookstore.controller.mapper.ItensSellMapper;
import br.com.tqi.bookstore.exception.BookQuantityNotEnougthToSellException;
import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.model.Client;
import br.com.tqi.bookstore.model.ItensEntry;
import br.com.tqi.bookstore.model.ItensSell;
import br.com.tqi.bookstore.repository.ItensEntryRepository;
import br.com.tqi.bookstore.repository.ItensSellRepository;
import br.com.tqi.bookstore.service.BookService;
import br.com.tqi.bookstore.service.ClientService;
import br.com.tqi.bookstore.service.ItensEntryService;
import br.com.tqi.bookstore.service.ItensSellService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItensSellServiceTest {

    @Mock
    private ItensSellRepository itensSellRepository;
    private ItensSellMapper itensSellMapper;

    @Mock
    private BookService bookService;
    @Mock
    private ClientService clientService;

    @InjectMocks
    private ItensSellService itensSellService;

    @Test
    void whenListItensSellIsCalledThenReturnAListOfItensSells() {
        ItensSell expectedSavedItensSell = ItensSellBuilder.builder().build().toItensSell();

        when(itensSellRepository.findAll()).thenReturn(Collections.singletonList(expectedSavedItensSell));

        List<ItensSell> foundListItensSells = itensSellService.findAll();

        assertThat(foundListItensSells, is(not(empty())));
        assertThat(foundListItensSells.get(0), is(equalTo(expectedSavedItensSell)));
    }

    @Test
    void whenValidItensSellIdIsGivenThenReturnAItensSell() throws NameAlreadyRegisteredException, IdNotFoundException, BookQuantityNotEnougthToSellException {
        ItensSell expectedSavedItensSell = ItensSellBuilder.builder().build().toItensSell();
        Book book = BookBuilder.builder().build().toBook();
        book.setQuantity(2);
        Client client = ClientBuilder.builder().build().toClient();

        when(bookService.findById(book.getId())).thenReturn(book);
        when(clientService.findById(client.getId())).thenReturn(client);

        ItensSell createdItensSell = itensSellService.create(expectedSavedItensSell);

        when(itensSellRepository.findById(expectedSavedItensSell.getId())).thenReturn(Optional.of(createdItensSell));

        assertThat(createdItensSell, is(equalTo(itensSellService.findById(createdItensSell.getId()))));
    }

    @Test
    void whenQuantityIsNotEnoughThenThrowAnException() throws NameAlreadyRegisteredException, IdNotFoundException, BookQuantityNotEnougthToSellException {
        ItensSell expectedSavedItensSell = ItensSellBuilder.builder().build().toItensSell();
        Book book = BookBuilder.builder().build().toBook();

        when(bookService.findById(book.getId())).thenReturn(book);

        assertThrows(BookQuantityNotEnougthToSellException.class, () -> itensSellService.verifyStock(expectedSavedItensSell));
    }


    @Test
    void whenItensSellInformedThenItShouldBeCreated() throws IdNotFoundException, NameAlreadyRegisteredException, BookQuantityNotEnougthToSellException {
        ItensSell expectedSavedItensSell = ItensSellBuilder.builder().build().toItensSell();
        String firstId = expectedSavedItensSell.getId();

        Book book = BookBuilder.builder().build().toBook();
        book.setQuantity(2);

        Client client = ClientBuilder.builder().build().toClient();

        when(bookService.findById(book.getId())).thenReturn(book);
        when(clientService.findById(client.getId())).thenReturn(client);

        ItensSell createdItensSell = itensSellService.create(expectedSavedItensSell);

        assertThat(createdItensSell.getId(), not(equalTo(firstId)));
        assertThat(createdItensSell.getClientId(), is(equalTo(expectedSavedItensSell.getClientId())));
        assertThat(createdItensSell.getBookIds(), is(equalTo(expectedSavedItensSell.getBookIds())));
    }

    @Test
    void whenNotRegisteredItensSellIdIsGivenThenThrowAnException() {
        ItensSell expectedSavedItensSell = ItensSellBuilder.builder().build().toItensSell();

        when(itensSellRepository.findById(expectedSavedItensSell.getId())).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> itensSellService.findById(expectedSavedItensSell.getId()));
    }

    @Test
    void whenABookIsSellThenAddSellOnClient() throws IdNotFoundException {
        Client clientToBeUpdated = ClientBuilder.builder().build().toClient();

        ItensSell newRegisterToBeSavedOnBook = ItensSellBuilder.builder().build().toItensSell();

        itensSellService.addItensSellOnClientList(newRegisterToBeSavedOnBook, clientToBeUpdated);

        assertThat(clientToBeUpdated.getItensSell(), hasItem(newRegisterToBeSavedOnBook));
    }

}
