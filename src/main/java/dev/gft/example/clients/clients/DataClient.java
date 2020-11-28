package dev.gft.example.clients.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.gft.example.clients.dtos.ClientDTO;

@FeignClient(value = "data", path = "/clients")
public interface DataClient {

    @GetMapping(path = "/")
    ResponseEntity<CollectionModel<ClientDTO>> findAllClients();

    @GetMapping(path = "/{id}")
    ResponseEntity<EntityModel<ClientDTO>> findOneById(@PathVariable("id") Long id);

    @PostMapping(path = "/")
    ResponseEntity<EntityModel<ClientDTO>> createOne(@RequestBody ClientDTO client);

    @PutMapping(path = "/{id}")
    ResponseEntity<EntityModel<ClientDTO>> updateOne(@PathVariable("id") Long id, @RequestBody ClientDTO client);

    @DeleteMapping(path = "/{id}")
    void deleteOne(@PathVariable("id") Long id);

}
