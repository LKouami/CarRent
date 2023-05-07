package com.example.carrent.controllers;

import com.example.carrent.entities.Client;
import com.example.carrent.repositories.ClientRepository;
import com.example.carrent.utils.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ClientController {
    private ClientRepository clientRepository;

    // Endpoint pour récupérer tous les employés
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping(path = "/clients")
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    // Endpoint pour récupérer un employé par son ID
    @GetMapping("/clients/{id}")
    public Optional<Client> getClientById(@PathVariable(value = "id") Long clientId) {
        return clientRepository.findById(clientId);
    }

    // Endpoint pour mettre à jour un employé
    @PutMapping("/clients/{id}")
    public Client updateClient(@PathVariable(value = "id") Long clientId, @RequestBody Client clientDetails) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
        client.setNom(clientDetails.getNom());
        client.setCin(clientDetails.getCin());
        client.setAdresse(clientDetails.getAdresse());
        Client updatedClient = clientRepository.save(client);
        return updatedClient;
    }

    // Endpoint pour supprimer un employé
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
        clientRepository.delete(client);
        return ResponseEntity.ok().build();
    }


}
