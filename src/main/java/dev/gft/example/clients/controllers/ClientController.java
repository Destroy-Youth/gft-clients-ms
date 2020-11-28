package dev.gft.example.clients.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.gft.example.clients.dtos.ClientDTO;
import dev.gft.example.clients.services.ClientsService;

@RestController
public class ClientController {

    @Autowired
    private ClientsService clientsService;

    @GetMapping(path = "/clients")
    public ResponseEntity<List<ClientDTO>> allClients() {
        return ResponseEntity.ok().body(clientsService.findAll());
    }

    @GetMapping(path = "/clients/{id}")
    public ResponseEntity<ClientDTO> oneClient(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(clientsService.findOneById(id));
    }

    @PostMapping(path = "/clients")
    public ResponseEntity<ClientDTO> newClient(@RequestBody ClientDTO client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientsService.createOne(client));
    }

    @PutMapping(path = "/clients/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO client) {
        return ResponseEntity.status(HttpStatus.OK).body(clientsService.updateOne(id, client));
    }

    @DeleteMapping(path = "/clients/{id}")
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable("id") Long id) {
        clientsService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path = "/clients-names")
    public ResponseEntity<List<String>> allClientsNames(@RequestParam("contains") String contains) {
        return ResponseEntity.ok().body(clientsService.getAllClientFullNames(contains));
    }

}
