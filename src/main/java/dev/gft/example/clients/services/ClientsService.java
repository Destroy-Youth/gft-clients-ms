package dev.gft.example.clients.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import dev.gft.example.clients.clients.DataClient;
import dev.gft.example.clients.dtos.ClientDTO;

@Service
public class ClientsService implements ICRUD<ClientDTO, Long> {

    @Autowired
    private DataClient dataClient;

    @Override
    public ClientDTO findOneById(Long id) {
        Optional<EntityModel<ClientDTO>> client = Optional.ofNullable(dataClient.findOneById(id).getBody());
        return client.orElseThrow(NoSuchElementException::new).getContent();
    }

    @Override
    public List<ClientDTO> findAll() {
        Optional<CollectionModel<ClientDTO>> clients = Optional.ofNullable(dataClient.findAllClients().getBody());
        return new ArrayList<>(clients.orElseThrow(NoSuchElementException::new).getContent());
    }

    @Override
    public ClientDTO createOne(ClientDTO newRegistry) {
        Optional<EntityModel<ClientDTO>> client = Optional.ofNullable(dataClient.createOne(newRegistry).getBody());
        return client.orElseThrow(NoSuchElementException::new).getContent();
    }

    @Override
    public ClientDTO updateOne(Long id, ClientDTO updatedRegistry) {
        findOneById(id);
        Optional<EntityModel<ClientDTO>> client = Optional
                .ofNullable(dataClient.updateOne(id, updatedRegistry).getBody());
        return client.orElseThrow(NoSuchElementException::new).getContent();
    }

    @Override
    public void deleteOne(Long id) {
        dataClient.deleteOne(id);
    }

    public List<String> getAllClientFullNames(String search) {

        Predicate<ClientDTO> filterByNameContains = client -> search.isEmpty() ? Boolean.TRUE
                : client.getFirstName().toLowerCase().contains(search);

        return findAll().stream().filter(filterByNameContains).map(client -> new StringBuilder()
                .append(client.getFirstName()).append(" ").append(client.getLastName()).toString())
                .collect(Collectors.toList());
    }

}
