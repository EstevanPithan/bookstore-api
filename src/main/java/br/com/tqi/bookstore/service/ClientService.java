package br.com.tqi.bookstore.service;

import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.model.Client;
import br.com.tqi.bookstore.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;


    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Client findById(String id) {
        return clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id)); //Find by id retorna um optinal
    }

    @Transactional
    public Client create(Client clientCreate) throws NameAlreadyRegisteredException {
        String uuid = getUUID();
        clientCreate.setId(uuid);
        clientRepository.save(clientCreate);
        return clientCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        clientRepository.deleteById(id);
    }

    @Transactional
    public Client update(String id, Client clientUpdate) {
        Client client = findById(id);
        client.setName(clientUpdate.getName());
        client.setCpf(clientUpdate.getCpf());
        client.setEmail(clientUpdate.getEmail());
        clientRepository.save(client);
        return client;
    }


//    private void verifyIfIsAlreadyRegistered(String cpf) throws NameAlreadyRegisteredException {
//        Optional<Client> optionalClient = clientRepository.findByCpf(cpf);
//        if (optionalClient.isPresent()) {
//            throw new NameAlreadyRegisteredException(cpf);
//        }
//    }
}
