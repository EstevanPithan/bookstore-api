package br.com.tqi.bookstore.test.service;

import br.com.tqi.bookstore.test.builder.ClientBuilder;
import br.com.tqi.bookstore.controller.mapper.ClientMapper;
import br.com.tqi.bookstore.exception.CpfAlreadyRegisteredException;
import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.model.*;
import br.com.tqi.bookstore.repository.ClientRepository;
import br.com.tqi.bookstore.service.AuthorService;
import br.com.tqi.bookstore.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    private ClientMapper clientMapper;
    @InjectMocks
    private ClientService clientService;

    @Test
    void whenClientInformedThenItShouldBeCreated() throws NameAlreadyRegisteredException, IdNotFoundException, CpfAlreadyRegisteredException {
        Client expectedSavedClient = ClientBuilder.builder().build().toClient();
        String firstId = expectedSavedClient.getId();

        when(clientRepository.findByCpf(expectedSavedClient.getCpf())).thenReturn(Optional.empty());


        Client createdClient = clientService.create(expectedSavedClient);

        assertThat(createdClient.getId(), not(equalTo(firstId)));
        assertThat(createdClient.getName(), is(equalTo(expectedSavedClient.getName())));
        assertThat(createdClient.getCpf(), is(equalTo(expectedSavedClient.getCpf())));
    }

    @Test
    void whenNotRegisteredClientIdIsGivenThenThrowAnException() throws IdNotFoundException {
        Client expectedSavedClient = ClientBuilder.builder().build().toClient();

        when(clientRepository.findById(expectedSavedClient.getId())).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> clientService.findById(expectedSavedClient.getId()));
    }

    @Test
    void whenValidClientIdIsGivenThenReturnAClient() throws  IdNotFoundException, CpfAlreadyRegisteredException {
        Client expectedSavedClient = ClientBuilder.builder().build().toClient();

        Client createdClient = clientService.create(expectedSavedClient);

        when(clientRepository.findById(expectedSavedClient.getId())).thenReturn(Optional.of(createdClient));

        assertThat(createdClient, is(equalTo(clientService.findById(createdClient.getId()))));
    }

    @Test
    void whenListClientIsCalledThenReturnAListOfClients() {
        Client expectedSavedClient = ClientBuilder.builder().build().toClient();

        when(clientRepository.findAll()).thenReturn(Collections.singletonList(expectedSavedClient));

        List<Client> foundListClients = clientService.findAll();

        assertThat(foundListClients, is(not(empty())));
        assertThat(foundListClients.get(0), is(equalTo(expectedSavedClient)));
    }

    @Test
    void whenDeleteIsCalledWithValidIdThenAClientShouldBeDeleted() throws IdNotFoundException {
        Client expectedDeletedClient = ClientBuilder.builder().build().toClient();
        String idClient = "123456";

        when(clientRepository.findById(idClient)).thenReturn(Optional.of(expectedDeletedClient));
        doNothing().when(clientRepository).deleteById(idClient);

        clientService.delete(idClient);

        verify(clientRepository, times(1)).findById(idClient);
        verify(clientRepository, times(1)).deleteById(idClient);
    }

    @Test
    void whenUpdateCalledThanReturnAClientUpdated() throws IdNotFoundException {
        Client expectedUpdatedClient = ClientBuilder.builder().build().toClient();
        Client update = new Client("567890", "client 2", "66677788890", "email", new ArrayList<ItensSell>());

        when(clientRepository.findById(expectedUpdatedClient.getId())).thenReturn(Optional.of(expectedUpdatedClient));

        clientService.update(expectedUpdatedClient.getId(), update);

        assertThat(expectedUpdatedClient.getName(), is(equalTo(update.getName())));
    }
}
